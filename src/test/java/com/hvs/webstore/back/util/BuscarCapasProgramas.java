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
import java.text.Normalizer;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public class BuscarCapasProgramas {

    private static final String DB_URL = "jdbc:mysql://localhost:3307/mysql-db?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";
    private static final String TMDB_API = "https://api.themoviedb.org/3";
    private static final String TMDB_IMAGE_BASE = "https://image.tmdb.org/t/p/w500";
    private static final double MIN_SCORE = 0.75;

    private static final Set<String> STOP = Set.of(
            "de", "da", "do", "das", "dos", "em", "no", "na", "nos", "nas",
            "e", "o", "a", "os", "as", "um", "uma", "uns", "umas",
            "the", "of", "and", "to", "in", "on", "for", "with", "vs");

    private static final Map<String, String> ALIAS = new LinkedHashMap<>(Map.ofEntries(
            Map.entry("buko no hero", "Boku no Hero Academia"),
            Map.entry("looney tones", "Looney Tunes"),
            Map.entry("stains gate 0", "Steins;Gate"),
            Map.entry("as meninas super poderosas", "As Meninas Superpoderosas"),
            Map.entry("as meninas super poderosas o filme", "The Powerpuff Girls Movie"),
            Map.entry("nanatsu no taizai", "The Seven Deadly Sins"),
            Map.entry("os cavaleiros do zodiaco asgard", "Os Cavaleiros do Zodiaco"),
            Map.entry("os cavaleiros do zodiaco doze casas", "Os Cavaleiros do Zodiaco"),
            Map.entry("os cavaleiros do zodiaco guerra galatica", "Os Cavaleiros do Zodiaco"),
            Map.entry("os cavaleiros do zodiaco cavaleiros de prata", "Os Cavaleiros do Zodiaco"),
            Map.entry("os cavaleiros do zodiaco poseidon", "Os Cavaleiros do Zodiaco"),
            Map.entry("os cavaleiros do zodiaco hades eliseos", "Os Cavaleiros do Zodiaco: A Saga de Hades"),
            Map.entry("tiny toons", "Tiny Toon Adventures"),
            Map.entry("digimon savers", "Digimon Data Squad"),
            Map.entry("medabots spirits", "Medabots")));

    record Pick(String title, String poster, double score, int queryWords, String query, String date) {}

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
        String auth = "Bearer " + apiKey;

        List<Long> ids = new ArrayList<>();
        List<String> nomes = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT id, nome FROM programa WHERE (capa_url IS NULL OR capa_url = '') ORDER BY id")) {
            while (rs.next()) {
                ids.add(rs.getLong(1));
                nomes.add(rs.getString(2));
            }
        }

        System.out.println("Programas sem capa: " + ids.size());

        int saved = 0;
        List<String> review = new ArrayList<>();
        List<String> notFound = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            for (int i = 0; i < ids.size(); i++) {
                long id = ids.get(i);
                String nome = nomes.get(i);
                if (nome == null || nome.isBlank()) continue;

                boolean movie = nome.startsWith("Cinema - ");
                String base = movie ? nome.substring("Cinema - ".length()) : nome;
                base = applyAlias(cleanName(base));

                List<String> queries = new ArrayList<>();
                queries.add(base);
                String noOva = base.replaceAll("(?i)\\s+OVA\\s*\\d*$", "").trim();
                if (!noOva.equals(base) && !noOva.isBlank() && !queries.contains(noOva)) queries.add(noOva);
                int dash = base.lastIndexOf(" - ");
                if (dash >= 0) {
                    String tail = base.substring(dash + 3).trim();
                    if (!tail.isBlank() && sigWords(tail).size() >= 2 && !queries.contains(tail)) queries.add(tail);
                }

                Pick best = null;
                for (String q : queries) {
                    if (q.isBlank()) continue;
                    JSONArray results = search(http, auth, movie ? "movie" : "tv", q);
                    Pick p = pickBest(q, results);
                    if (p != null && (best == null || better(p, best))) best = p;
                    Thread.sleep(400);
                    if (best != null && best.score() >= 2.0) break;
                }

                if (best == null) {
                    System.out.println("[" + id + "] " + nome + " -> nao encontrado");
                    notFound.add("[" + id + "] " + nome);
                } else if (best.score() >= MIN_SCORE) {
                    String fullUrl = TMDB_IMAGE_BASE + best.poster();
                    try (PreparedStatement ps = conn.prepareStatement(
                            "UPDATE programa SET capa_url = ? WHERE id = ?")) {
                        ps.setString(1, fullUrl);
                        ps.setLong(2, id);
                        ps.executeUpdate();
                    }
                    System.out.println("[" + id + "] " + nome + " => \"" + best.title()
                            + "\" [score=" + fmt(best.score()) + "] " + fullUrl);
                    saved++;
                } else {
                    System.out.println("[" + id + "] " + nome + " => REVIEW \"" + best.title()
                            + "\" [score=" + fmt(best.score()) + "] query=\"" + best.query() + "\" "
                            + TMDB_IMAGE_BASE + best.poster() + " (nao salvo)");
                    review.add("[" + id + "] " + nome + " => \"" + best.title()
                            + "\" [score=" + fmt(best.score()) + "] " + TMDB_IMAGE_BASE + best.poster());
                }

                Thread.sleep(400);
            }
        }

        System.out.println("\n=== RESUMO ===");
        System.out.println("Salvas: " + saved);
        System.out.println("Revisar (" + review.size() + "):");
        for (String s : review) System.out.println("  " + s);
        System.out.println("Nao encontrados (" + notFound.size() + "):");
        for (String s : notFound) System.out.println("  " + s);
    }

    private static String applyAlias(String base) {
        String aliased = ALIAS.get(norm(base));
        if (aliased != null) return aliased;
        if (norm(base).startsWith("attack on titans")) {
            return base.replaceFirst("(?i)attack on titans", "Attack on Titan");
        }
        return base;
    }

    private static boolean better(Pick a, Pick b) {
        if (a.score() != b.score()) return a.score() > b.score();
        return a.queryWords() > b.queryWords();
    }

    private static JSONArray search(HttpClient http, String auth, String kind, String query) {
        for (int attempt = 1; attempt <= 2; attempt++) {
            try {
                String url = TMDB_API + "/search/" + kind
                        + "?query=" + java.net.URLEncoder.encode(query, java.nio.charset.StandardCharsets.UTF_8)
                        + "&language=pt-BR";
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .timeout(Duration.ofSeconds(15))
                        .header("Authorization", auth)
                        .header("Accept", "application/json")
                        .GET()
                        .build();
                HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == 200) {
                    JSONObject json = new JSONObject(response.body());
                    JSONArray results = json.optJSONArray("results");
                    return results != null ? results : new JSONArray();
                }
                System.out.println("  (TMDB HTTP " + response.statusCode() + " query=\"" + query + "\" tentativa " + attempt + ")");
                Thread.sleep(5000);
            } catch (Exception e) {
                System.out.println("  (TMDB erro query=\"" + query + "\": " + e.getMessage() + ")");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        return new JSONArray();
    }

    private static Pick pickBest(String query, JSONArray results) {
        List<String> qwords = sigWords(query);
        if (qwords.isEmpty()) return null;
        String qnorm = norm(query);
        Pick best = null;
        for (int i = 0; i < results.length(); i++) {
            JSONObject r = results.getJSONObject(i);
            String poster = r.optString("poster_path", null);
            if (poster == null || poster.equals("null") || poster.isBlank()) continue;
            String name = r.optString("name", r.optString("title", ""));
            String orig = r.optString("original_name", r.optString("original_title", ""));
            String title = !name.isBlank() ? name : orig;
            double s = Math.max(score(qwords, name), score(qwords, orig));
            if (!qnorm.isBlank()
                    && (norm(name).equals(qnorm) || (!orig.isBlank() && norm(orig).equals(qnorm)))) {
                s = 2.0;
            }
            String date = r.optString("first_air_date", r.optString("release_date", ""));
            if (best == null || s > best.score()
                    || (s == best.score() && compareDate(date, best.date()) < 0)
                    || (s == best.score() && compareDate(date, best.date()) == 0
                        && norm(title).split(" ").length < norm(best.title()).split(" ").length)) {
                best = new Pick(title, poster, s, qwords.size(), query, date == null ? "" : date);
            }
        }
        return best;
    }

    private static int compareDate(String a, String b) {
        String x = (a == null || a.isBlank()) ? "9999" : a;
        String y = (b == null || b.isBlank()) ? "9999" : b;
        return x.compareTo(y);
    }

    private static double score(List<String> qwords, String candidate) {
        if (candidate == null || candidate.isBlank()) return 0;
        String c = norm(candidate);
        int hit = 0;
        for (String w : qwords) if (c.contains(w)) hit++;
        return (double) hit / qwords.size();
    }

    private static List<String> sigWords(String s) {
        String[] tokens = norm(s).split(" ");
        List<String> out = new ArrayList<>();
        for (String w : tokens) {
            if (w.length() > 2 && !STOP.contains(w) && !out.contains(w)) out.add(w);
        }
        if (!out.isEmpty()) return out;
        for (String w : tokens) {
            if (w.length() >= 2 && !STOP.contains(w) && !out.contains(w)) out.add(w);
        }
        if (!out.isEmpty()) return out;
        for (String w : tokens) {
            if (!w.isBlank() && !out.contains(w)) out.add(w);
        }
        return out;
    }

    private static String norm(String s) {
        String n = Normalizer.normalize(s.toLowerCase(Locale.ROOT), Normalizer.Form.NFD)
                .replaceAll("[\\u0300-\\u036f]", "");
        n = n.replaceAll("(?<=[a-z])(?=[0-9])|(?<=[0-9])(?=[a-z])", " ");
        return n.replaceAll("[^a-z0-9 ]", " ").replaceAll("\\s+", " ").trim();
    }

    private static String cleanName(String name) {
        String cleaned = name.trim();
        for (String suffix : List.of(" - Legendado", " - Legandado", " - Dublado", " - Incompleto",
                " Legendado", " Dublado", " Incompleto")) {
            if (cleaned.endsWith(suffix)) {
                cleaned = cleaned.substring(0, cleaned.length() - suffix.length());
            }
        }
        return cleaned.trim();
    }

    private static String fmt(double d) {
        return String.format(Locale.ROOT, "%.2f", d);
    }
}
