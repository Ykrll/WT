package org.example.dao;

import org.example.db.DatabaseUtil;
import org.example.model.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao {

    public void save(Review review) throws Exception {
        String sql = """
            INSERT INTO review(book_id, reader_id, text, rating, review_date)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     sql,
                     PreparedStatement.RETURN_GENERATED_KEYS
             )) {

            ps.setInt(1, review.getBookId());
            ps.setInt(2, review.getReaderId());
            ps.setString(3, review.getText());
            ps.setInt(4, review.getRating());
            ps.setDate(5, java.sql.Date.valueOf(review.getReviewDate()));

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    review.setId(rs.getInt(1));
                }
            }
        }
    }


    public List<Review> findAllSortedByDate() throws Exception {
        List<Review> reviews = new ArrayList<>();

        String sql = """
            SELECT id, book_id, reader_id, text, rating, review_date
            FROM review
            ORDER BY review_date DESC
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Review r = new Review(
                        rs.getInt("book_id"),
                        rs.getInt("reader_id"),
                        rs.getString("text"),
                        rs.getInt("rating"),
                        rs.getDate("review_date").toLocalDate()
                );
                r.setId(rs.getInt("id"));
                reviews.add(r);
            }
        }
        return reviews;
    }
}
