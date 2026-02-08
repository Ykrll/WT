package org.example.dao;

import org.example.db.DatabaseUtil;
import org.example.model.Reader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ReaderDao {

    public void save(Reader reader) throws Exception {
        String sql = "INSERT INTO reader(name) VALUES (?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     sql,
                     PreparedStatement.RETURN_GENERATED_KEYS
             )) {

            ps.setString(1, reader.getName());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    reader.setId(rs.getInt(1));
                }
            }
        }
    }

    public List<Reader> findAll() throws Exception {
        List<Reader> readers = new ArrayList<>();

        String sql = "SELECT id, name FROM reader";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Reader r = new Reader(rs.getString("name"));
                r.setId(rs.getInt("id"));
                readers.add(r);
            }
        }

        return readers;
    }
}
