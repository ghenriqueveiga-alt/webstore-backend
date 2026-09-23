package com.hvs.webstore.back.util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class BuscarCapasProgramas {

    private static final String DB_URL = "jdbc:mysql://localhost:3307/mysql-db?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";
    private static final String TMDB_SEARCH_URL = "https://api.themoviedb.org/3/search/tv";
    private static final String TMDB_IMAGE_BASE = "https://image.tmdb.org/t/p/w500";

    public static void main(String[] args) throws Exception {

        String apiKey = System.getenv("TMDB_API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            System.out.println("Defina a variavel de ambiente TMDB_API_KEY.");
            System.out.println("Obtenha gratis em: https://www.themoviedb.org/settings/api");
            return;
        }

        HttpClient http = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            List<long[]> programas = new ArrayList<>();
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(
                         "SELECT id, nome FROM programa WHERE status_desc = 'Active' AND (capa_url IS NULL OR capa_url = '')")) {
                while (rs.next()) {
                    programas.add(new long[]{rs.getLong("id")});
                }
            }

            System.out.println("Programas sem capa: " + programas.size());

            int found = 0;
            int notFound = 0;

            for (long[] row : programas) {
                long id = row[0];

                String nome = null;
                try (PreparedStatement ps = conn.prepareStatement("SELECT nome FROM programa WHERE id = ?")) {
                    ps.setLong(1, id);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) nome = rs.getString("nome");
                    }
                }

                if (nome == null || nome.isBlank()) continue;

                String searchName = cleanName(nome);
                String url = TMDB_SEARCH_URL
                        + "?query=" + java.net.URLEncoder.encode(searchName, java.nio.charset.StandardCharsets.UTF_8)
                        + "&language=pt-BR";

                try {
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .timeout(Duration.ofSeconds(15))
                            .header("Authorization", "Bearer " + apiKey)
                            .header("Accept", "application/json")
                            .GET()
                            .build();

                    HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());

                    if (response.statusCode() != 200) {
                        System.out.println("[" + id + "] " + nome + " -> erro HTTP " + response.statusCode());
                        notFound++;
                        Thread.sleep(300);
                        continue;
                    }

                    JSONObject json = new JSONObject(response.body());
                    JSONArray results = json.getJSONArray("results");

                    if (results.isEmpty()) {
                        System.out.println("[" + id + "] " + nome + " -> nao encontrado");
                        notFound++;
                    } else {
                        JSONObject first = results.getJSONObject(0);
                        String posterPath = first.optString("poster_path", null);

                        if (posterPath != null && !posterPath.equals("null")) {
                            String fullUrl = TMDB_IMAGE_BASE + posterPath;
                            try (PreparedStatement ps = conn.prepareStatement(
                                    "UPDATE programa SET capa_url = ? WHERE id = ?")) {
                                ps.setString(1, fullUrl);
                                ps.setLong(2, id);
                                ps.executeUpdate();
                            }
                            System.out.println("[" + id + "] " + nome + " -> " + fullUrl);
                            found++;
                        } else {
                            System.out.println("[" + id + "] " + nome + " -> sem poster");
                            notFound++;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("[" + id + "] " + nome + " -> erro: " + e.getMessage());
                    notFound++;
                }

                Thread.sleep(300);
            }

            System.out.println("\n=== RESUMO ===");
            System.out.println("Encontrados: " + found);
            System.out.println("Nao encontrados: " + notFound);
        }
    }

    private static String cleanName(String name) {
        String cleaned = name;
        for (String suffix : List.of(" - Legendado", " - Incompleto", " - Dublado", " Legendado", " Incompleto", " Dublado")) {
            if (cleaned.endsWith(suffix)) {
                cleaned = cleaned.substring(0, cleaned.length() - suffix.length());
            }
        }
        return cleaned.trim();
    }
}
