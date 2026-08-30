package com.hvs.webstore.back;

import com.hvs.webstore.back.infra.media.VisualFusionIntroDetector;
import com.hvs.webstore.back.infra.media.VisualFusionEndingDetector;
import com.hvs.webstore.back.infra.media.MediaPathResolverImpl;
import com.hvs.webstore.back.app.service.IntroDetector;
import com.hvs.webstore.back.app.service.EndingDetector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReDetectarIntroEnding {

    public static void main(String[] args) throws Exception {
        final String url = "jdbc:mysql://localhost:3307/mysql-db?useUnicode=true&characterEncoding=utf8";
        final String user = "root";
        final String pass = "root";
        final String modo = args.length > 0 ? args[0] : "ambos"; // intro | ending | ambos
        final int limite = args.length > 1 ? Integer.parseInt(args[1]) : Integer.MAX_VALUE; // limite de programas por tabela

        final Connection conn = DriverManager.getConnection(url, user, pass);
        conn.setAutoCommit(false);

        final MediaPathResolverImpl resolver = new MediaPathResolverImpl("F:\\");

        final IntroDetector introDetector = new VisualFusionIntroDetector();
        final EndingDetector endingDetector = new VisualFusionEndingDetector();

        if (modo.equals("intro") || modo.equals("ambos")) {
            reprocessar(conn, "intro_detectado", "i", true, introDetector, limite, resolver);
        }
        if (modo.equals("ending") || modo.equals("ambos")) {
            reprocessar(conn, "ending_detectado", "e", false, endingDetector, limite, resolver);
        }

        conn.commit();
        conn.close();
        System.out.println("DONE");
    }

    @SuppressWarnings("unchecked")
    private static void reprocessar(Connection conn, String tabela, String alias,
                                    boolean isIntro, Object detector, int limite,
                                    MediaPathResolverImpl resolver) throws Exception {

        // 1. programas com episodios nao detectados
        final String sqlProgramas = "SELECT DISTINCT ep.programa_id FROM " + tabela + " " + alias
                + " JOIN episodio ep ON ep.id = " + alias + ".episodio_id "
                + " JOIN arquivo a ON a.id = ep.arquivo_id "
                + " WHERE " + alias + ".detectado = 0 AND a.caminho IS NOT NULL ORDER BY ep.programa_id";
        final List<Long> programas = new ArrayList<>();
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sqlProgramas)) {
            while (rs.next()) {
                programas.add(rs.getLong(1));
            }
        }
        System.out.println("[" + tabela + "] programas com nao detectados: " + programas.size());

        int totalNovos = 0;
        for (Long programaId : programas) {
            if (limite-- <= 0) {
                break;
            }
            // 2. todos os caminhos do programa (para dar contexto ao detector)
            final String sqlCaminhos = "SELECT a.caminho, ep.id FROM episodio ep JOIN arquivo a ON a.id = ep.arquivo_id WHERE ep.programa_id = ? AND a.caminho IS NOT NULL";
            final Map<String, Long> caminhoParaEp = new LinkedHashMap<>();
            try (PreparedStatement ps = conn.prepareStatement(sqlCaminhos)) {
                ps.setLong(1, programaId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        caminhoParaEp.put(resolver.resolve(rs.getString(1)), rs.getLong(2));
                    }
                }
            }
            if (caminhoParaEp.isEmpty()) {
                continue;
            }
            final List<String> caminhos = new ArrayList<>(caminhoParaEp.keySet());

            final Map<String, ?> detectados;
            try {
                if (isIntro) {
                    detectados = ((IntroDetector) detector).detectIntro(caminhos);
                } else {
                    detectados = ((EndingDetector) detector).detectEnding(caminhos);
                }
            } catch (Exception e) {
                System.out.println("  erro no programa " + programaId + ": " + e.getMessage());
                continue;
            }

            // 3. atualizar apenas os nao detectados
            final String sqlUpdate = "UPDATE " + tabela + " SET inicio = ?, duracao = ?, fim = ?, confianca = ?, detectado = ? WHERE episodio_id = ? AND detectado = 0";
            try (PreparedStatement ps = conn.prepareStatement(sqlUpdate)) {
                for (Map.Entry<String, ?> e : detectados.entrySet()) {
                    final Long episodioId = caminhoParaEp.get(resolver.resolve(e.getKey()));
                    if (episodioId == null) {
                        continue;
                    }
                    final boolean det;
                    final long ini;
                    final double dur;
                    final double conf;
                    if (isIntro) {
                        final IntroDetector.DetectedIntro d = (IntroDetector.DetectedIntro) e.getValue();
                        det = d.aDetectado();
                        ini = d.aInicioSegundos();
                        dur = d.aDuracaoSegundos();
                        conf = d.aConfianca();
                    } else {
                        final EndingDetector.DetectedEnding d = (EndingDetector.DetectedEnding) e.getValue();
                        det = d.aDetectado();
                        ini = d.aInicioSegundos();
                        dur = d.aDuracaoSegundos();
                        conf = d.aConfianca();
                    }
                    ps.setTimestamp(1, new java.sql.Timestamp(ini * 1000L));
                    ps.setTimestamp(2, new java.sql.Timestamp(Math.round(dur * 1000.0)));
                    ps.setTimestamp(3, new java.sql.Timestamp((ini + Math.round(dur)) * 1000L));
                    ps.setDouble(4, conf);
                    ps.setBoolean(5, det);
                    ps.setLong(6, episodioId);
                    ps.addBatch();
                }
                final int[] res = ps.executeBatch();
                int n = 0;
                for (int r : res) {
                    if (r > 0) {
                        n++;
                    }
                }
                totalNovos += n;
                System.out.println("  programa " + programaId + ": atualizados " + n);
            }
            conn.commit();
        }
        System.out.println("[" + tabela + "] TOTAL atualizados: " + totalNovos);
    }
}
