package org.example.dao;

import org.example.db.DatabaseUtil;
import org.example.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class BookDao {

    public void save(Book book) throws Exception {
        String sql = """
            INSERT INTO book(title, author, library_id, status_id)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     sql,
                     PreparedStatement.RETURN_GENERATED_KEYS
             )) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getLibraryId());

            if (book.getStatusId() != null) {
                ps.setInt(4, book.getStatusId());
            } else {
                ps.setNull(4, java.sql.Types.INTEGER);
            }

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    book.setId(rs.getInt(1));
                }
            }
        }
    }

    public List<Book> findAll() throws Exception {
        List<Book> books = new ArrayList<>();

        String sql = """
            SELECT id, title, author, library_id, status_id
            FROM book
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Book b = new Book(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("library_id"),
                        (Integer) rs.getObject("status_id")
                );
                b.setId(rs.getInt("id"));
                books.add(b);
            }
        }

        return books;
    }

    public void updateStatus(int bookId, int statusId) throws Exception {
        String sql = "UPDATE book SET status_id = ? WHERE id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, statusId);
            ps.setInt(2, bookId);
            ps.executeUpdate();
        }
    }

    public void deleteById(int id) throws Exception {
        String sql = "DELETE FROM book WHERE id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
