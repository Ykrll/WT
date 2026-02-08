package org.example.dao;

import org.example.db.DatabaseUtil;
import org.example.model.Library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class LibraryDao {

    public void save(Library library) throws Exception {
        String sql = "INSERT INTO library(name) VALUES (?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     sql,
                     PreparedStatement.RETURN_GENERATED_KEYS
             )) {

            ps.setString(1, library.getName());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    library.setId(rs.getInt(1));
                }
            }
        }
    }

    public List<Library> findAll() throws Exception {
        List<Library> libraries = new ArrayList<>();

        String sql = "SELECT id, name FROM library";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Library l = new Library(rs.getString("name"));
                l.setId(rs.getInt("id"));
                libraries.add(l);
            }
        }
        return libraries;
    }

    public void update(Library library) throws Exception {
        String sql = "UPDATE library SET name = ? WHERE id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, library.getName());
            ps.setInt(2, library.getId());

            ps.executeUpdate();
        }
    }

    public void deleteById(int id) throws Exception {
        String sql = "DELETE FROM library WHERE id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
