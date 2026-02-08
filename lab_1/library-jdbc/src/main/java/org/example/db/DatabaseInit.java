package org.example.db;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInit {

    public static void init() throws Exception {
        try (Connection conn = DatabaseUtil.getConnection();
             Statement st = conn.createStatement()) {

            InputStream is = DatabaseInit.class
                    .getClassLoader()
                    .getResourceAsStream("schema.sql");

            if (is == null) {
                throw new RuntimeException("schema.sql not found");
            }

            String sql = new String(is.readAllBytes(), StandardCharsets.UTF_8);

            for (String statement : sql.split(";")) {
                if (!statement.trim().isEmpty()) {
                    st.execute(statement);
                }
            }
        }
    }
}
