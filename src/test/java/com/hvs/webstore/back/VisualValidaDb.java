package com.hvs.webstore.back;

import com.hvs.webstore.back.infra.media.VisualFusion;
import com.hvs.webstore.back.infra.media.MediaPathResolverImpl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class VisualValidaDb {

    public static void main(String[] args) throws Exception {

        final String url = "jdbc:mysql://localhost:3307/mysql-db?useUnicode=true&characterEncoding=utf8";
        final String user = "root";
        final String pass = "root";
        final String tabela = args.length > 0 ? args[0] : "intro"; // intro | ending
        final int limiteDirs = args.length > 1 ? Integer.parseInt(args[1]) : Integer.MAX_VALUE;
        final boolean seco = args.length > 2 && args[2].equals("seco");

        final String nomeTabela = tabela.equals("ending") ? "ending_detectado" : "intro_detectado";
        final MediaPathResolverImpl resolver = new MediaPathResolverImpl("F:\\");
        final Connection conn = DriverManager.getConnection(url, user, pass);
        conn.setAutoCommit(false);

        final String sql = "SELECT " + nomeTabela + ".id, a.caminho, "
                + " TIMESTAMPDIFF(SECOND, '1970-01-01 00:00:00', " + nomeTabela + ".inicio) AS ini, "
                + " TIMESTAMPDIFF(SECOND, '1970-01-01 00:00:00', " + nomeTabela + ".duracao) AS dur, "
                + " " + nomeTabela + ".confianca "
                + " FROM " + nomeTabela
                + " JOIN episodio ep ON ep.id = " + nomeTabela + ".episodio_id "
                + " JOIN arquivo a ON a.id = ep.arquivo_id "
                + " WHERE " + nomeTabela + ".detectado = 1 AND a.caminho IS NOT NULL"
                + " AND TIMESTAMPDIFF(SECOND, '1970-01-01 00:00:00', " + nomeTabela + ".duracao) >= 12"
                + " ORDER BY a.caminho";

        final Map<String, List<Registro>> porDir = new TreeMap<>();
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                final long id = rs.getLong(1);
                final String caminho = rs.getString(2);
                final long ini = rs.getLong(3);
                final long dur = rs.getLong(4);
                final double conf = rs.getDouble(5);
                final String resolvido = resolver.resolve(caminho);
                final Path p = Paths.get(resolvido);
                final String dir = p.getParent() != null ? p.getParent().toString() : "";
                porDir.computeIfAbsent(dir, k -> new ArrayList<>()).add(new Registro(id, resolvido, ini, dur, conf));
            }
        }

        final VisualFusion fusion = new VisualFusion();
        int dirsValidos = 0;
        int invalidadas = 0;
        int l = limiteDirs;
        for (Map.Entry<String, List<Registro>> e : porDir.entrySet()) {
            if (l-- <= 0) {
                break;
            }
            final List<Registro> registros = e.getValue();
            if (registros.size() < 2) {
                continue;
            }
            dirsValidos++;
            final List<VisualFusion.VisualCandidato> candidatos = new ArrayList<>();
            for (Registro r : registros) {
                candidatos.add(new VisualFusion.VisualCandidato(r.caminho(), r.ini(), (double) r.dur(), r.conf(), true));
            }
            final List<VisualFusion.VisualCandidato> refinados = fusion.aplicar(candidatos);
            final Map<String, VisualFusion.VisualCandidato> porCaminhoResolvido = new LinkedHashMap<>();
            for (VisualFusion.VisualCandidato c : refinados) {
                porCaminhoResolvido.put(resolver.resolve(c.caminho()), c);
            }
            for (Registro r : registros) {
                final VisualFusion.VisualCandidato c = porCaminhoResolvido.get(r.caminho());
                if (c == null || c.detectado()) {
                    continue;
                }
                System.out.println("  INVALIDADO dir=" + e.getKey());
                System.out.println("    " + r.caminho());
                invalidadas++;
                if (seco) {
                    continue;
                }
                final String sqlUpd = "UPDATE " + nomeTabela
                        + " SET inicio = NULL, duracao = NULL, fim = NULL, confianca = NULL, detectado = 0 WHERE id = ?";
                try (PreparedStatement ps = conn.prepareStatement(sqlUpd)) {
                    ps.setObject(1, r.id(), Types.BIGINT);
                    ps.addBatch();
                    ps.executeBatch();
                }
            }
            conn.commit();
        }

        System.out.println("diretorios com >=2 detectados: " + dirsValidos);
        System.out.println("total invalidados: " + invalidadas);
        if (seco) {
            System.out.println("MODO SECO - nenhum update aplicado");
        }
        conn.close();
    }

    private record Registro(long id, String caminho, long ini, long dur, double conf) {
    }
}