package org.example.db;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Statement;


public class DatabaseInit {

    public static void init() throws Exception {
        try (
            Connection conn = DatabaseUtil.getConnection();
            Statement stmt = conn.createStatement();
            InputStream is = DatabaseInit.class.getClassLoader().getResourceAsStream("schema.sql")
        ) {
            if (is == null) {
                throw new RuntimeException("schema.sql not found in resources");
            }

            String sql = new String(is.readAllBytes(), StandardCharsets.UTF_8);

            for (String s : sql.split(";")) {
                if (!s.trim().isEmpty()) {
                    stmt.execute(s);
                }
            }
        }
    }
}
