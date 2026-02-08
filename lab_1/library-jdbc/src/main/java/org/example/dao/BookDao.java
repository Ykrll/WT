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
        String sql = "INSERT INTO book(title, author) VALUES (?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.executeUpdate();
        }
    }

    public List<Book> findAll() throws Exception {
        List<Book> books = new ArrayList<>();

        String sql = "SELECT id, title, author FROM book";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Book b = new Book();
                b.setId(rs.getInt("id"));
                b = new Book(rs.getString("title"), rs.getString("author"));
                books.add(b);
            }
        }
        return books;
    }
}
