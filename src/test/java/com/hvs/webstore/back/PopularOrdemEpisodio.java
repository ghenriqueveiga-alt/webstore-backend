package com.hvs.webstore.back;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PopularOrdemEpisodio {

    private static final String DB_URL = "jdbc:mysql://localhost:3307/mysql-db?useUnicode=true&characterEncoding=utf8";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";

    public static void main(String[] args) throws Exception {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        conn.setAutoCommit(false);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(
                "SELECT e.id, a.caminho " +
                "FROM episodio e JOIN arquivo a ON a.id = e.arquivo_id " +
                "WHERE a.caminho IS NOT NULL ORDER BY e.id");

        int total = 0;
        while (rs.next()) {
            long id = rs.getLong(1);
            String caminho = rs.getString(2);
            int ordem = extrairOrdemDoCaminho(caminho);

            try (PreparedStatement ps = conn.prepareStatement(
                    "UPDATE episodio SET Ordem = ? WHERE id = ?")) {
                ps.setInt(1, ordem);
                ps.setLong(2, id);
                ps.executeUpdate();
                total++;
                if (total % 100 == 0) {
                    System.out.print("Processados: " + total + "...");
                }
            }
        }
        conn.commit();
        conn.close();
        System.out.println("\nConcluído. Total atualizados: " + total);
    }

    /**
     * Extrai o número da ordem a partir do nome da pasta que contém o episódio.
     * Padrão esperado na pasta: "N_Nome" (ex: "1_Dragon Ball", "2_Dragon Ball Z").
     * Se a pasta não iniciar com número seguido de underscore, retorna 0.
     */
    private static int extrairOrdemDoCaminho(String caminho) {
        if (caminho == null || caminho.isBlank()) return 0;

        // Normaliza o caminho: corrige barras duplas e remove prefixo F:\ se existir
        String normalizado = caminho.replace("\\\\", "\\");
        if (normalizado.startsWith("F:\\")) {
            normalizado = normalizado.substring(3);
        }

        // Divide o caminho em partes (pastas e arquivo)
        String[] partes = normalizado.split("\\\\");
        if (partes.length < 2) return 0;

        // A pasta que contém o episódio é a penúltima parte do caminho
        // Ex: "Dragon Ball\\1_Dragon Ball\\Episode.mp4" -> partes[length-2] = "1_Dragon Ball"
        String nomePasta = partes[partes.length - 2];

        // Padrão: número no início da string, antes de underscore
        Pattern pattern = Pattern.compile("^(\\d+)");
        Matcher matcher = pattern.matcher(nomePasta);
        if (matcher.find()) {
            try {
                return Integer.parseInt(matcher.group(1));
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }
}