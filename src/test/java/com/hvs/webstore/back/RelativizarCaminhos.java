package com.hvs.webstore.back;

import com.hvs.webstore.back.infra.media.MediaPathResolverImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RelativizarCaminhos {

    public static void main(String[] args) throws Exception {

        final String url = "jdbc:mysql://localhost:3307/mysql-db?useUnicode=true&characterEncoding=utf8";
        final String user = "root";
        final String pass = "root";
        final boolean aplicar = args.length > 0 && args[0].equals("aplicar");

        final MediaPathResolverImpl resolver = new MediaPathResolverImpl("F:\\");

        final Connection conn = DriverManager.getConnection(url, user, pass);
        conn.setAutoCommit(false);

        final List<Object[]> linhas = new ArrayList<>();
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("SELECT id, caminho FROM arquivo ORDER BY id")) {
            while (rs.next()) {
                linhas.add(new Object[]{rs.getLong(1), rs.getString(2)});
            }
        }
        System.out.println("total arquivos: " + linhas.size());

        int convertidos = 0;
        int mantidos = 0;
        try (PreparedStatement ps = conn.prepareStatement("UPDATE arquivo SET caminho = ? WHERE id = ?")) {
            for (Object[] linha : linhas) {
                final Long id = (Long) linha[0];
                final String caminho = (String) linha[1];
                final String relativo = resolver.relativize(caminho);
                if (!java.util.Objects.equals(caminho, relativo)) {
                    convertidos++;
                    if (aplicar) {
                        ps.setString(1, relativo);
                        ps.setLong(2, id);
                        ps.addBatch();
                    }
                } else {
                    mantidos++;
                }
            }
            if (aplicar) {
                final int[] res = ps.executeBatch();
                int n = 0;
                for (int r : res) {
                    if (r > 0) {
                        n++;
                    }
                }
                System.out.println("convertidos (aplicado): " + n);
            }
        }
        System.out.println("convertidos (previsto): " + convertidos);
        System.out.println("mantidos (ja relativo ou fora da raiz): " + mantidos);
        if (aplicar) {
            conn.commit();
            System.out.println("COMMIT aplicado.");
        } else {
            System.out.println("MODO PREVISAO - nenhum update aplicado. Rode com arg 'aplicar' para persistir.");
        }
        conn.close();
    }
}