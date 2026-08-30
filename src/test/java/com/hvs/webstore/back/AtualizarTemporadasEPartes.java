package com.hvs.webstore.back;

import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AtualizarTemporadasEPartes {

    private static final String DB_URL = "jdbc:mysql://localhost:3307/mysql-db?useUnicode=true&characterEncoding=utf8";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";
    private static final String F_ROOT = "F:\\";

    public static void main(String[] args) throws Exception {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        conn.setAutoCommit(false);

        // 1. Carregar todos os episódios com o caminho do arquivo
        List<Map<String, Object>> episodios = new ArrayList<>();
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(
                     "SELECT e.id, a.caminho " +
                     "FROM episodio e JOIN arquivo a ON a.id = e.arquivo_id " +
                     "WHERE a.caminho IS NOT NULL ORDER BY e.id")) {

            while (rs.next()) {
                Map<String, Object> m = new HashMap<>();
                m.put("id", rs.getLong("id"));
                m.put("caminho", rs.getString("caminho"));
                episodios.add(m);
            }
        }
        System.out.println("Episódios carregados: " + episodios.size());

        // 2. Para cada episódio, analisar o caminho em F:\ e atualizar temporada/parte
        for (Map<String, Object> ep : episodios) {
            long id = (long) ep.get("id");
            String dbCaminho = (String) ep.get("caminho");

            // Normalizar: converter \\\\ em \\ e remover prefixo F:\ duplicado
            String normalized = dbCaminho.replace("\\\\", "\\");
            if (normalized.startsWith("F:\\")) {
                normalized = normalized.substring(3);
            }

            // Dividir em segmentos de pasta/arquivo
            String[] segs = normalized.split("\\\\");

            int temporada = 0;
            String parteStr = null;

            // Varrer todas as pastas em busca de "Xª Temporada" e "Parte Y"
            for (String pasta : segs) {
                if (pasta == null || pasta.isEmpty()) continue;

                // Padrão: "1ª Temporada", "2ª Temporada", "3ª Temporada", etc.
                Matcher ms = Pattern.compile("(\\d+)\\ª?\\s*Temporada").matcher(pasta);
                if (ms.find()) {
                    temporada = Integer.parseInt(ms.group(1));
                }

                // Padrão: "Parte 1", "Parte 2", etc.
                Matcher mp = Pattern.compile("Parte\\s+(\\d+)").matcher(pasta);
                if (mp.find()) {
                    parteStr = mp.group(1);
                }
            }

            // Padrão especial: "1_Dragon Ball", "2_Dragon Ball Z", "3_Dragon Ball GT", etc.
            // → temporada = número antes do underline, se ainda não encontrado
            if (temporada == 0) {
                for (String pasta : segs) {
                    Matcher ms = Pattern.compile("^(\\d+)_").matcher(pasta);
                    if (ms.find()) {
                        temporada = Integer.parseInt(ms.group(1));
                        break;
                    }
                }
            }

            // Atualizar na tabela episódio
            try (PreparedStatement ps = conn.prepareStatement(
                    "UPDATE episodio SET temporada = ?, parte = ? WHERE id = ?")) {
                ps.setInt(1, temporada);
                ps.setString(2, parteStr != null ? parteStr : null);
                ps.setLong(3, id);
                ps.executeUpdate();
                System.out.printf("Atualizado id=%d -> temporada=%d, parte=%s [%s/%s]%n",
                        id, temporada, parteStr != null ? parteStr : "NULL",
                        temporada > 0 ? Integer.toString(temporada) : "0",
                        parteStr != null ? parteStr : "null");
            }
        }

        conn.commit();
        conn.close();
        System.out.println("Concluído.");
    }
}