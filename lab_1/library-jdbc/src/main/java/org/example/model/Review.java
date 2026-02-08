package org.example.model;

import java.time.LocalDate;


public class Review {

    private int id;
    private int bookId;
    private int readerId;
    private String text;
    private int rating;
    private LocalDate reviewDate;

    public Review() {}

    public Review(int bookId, int readerId, String text, int rating, LocalDate reviewDate) {
        this.bookId = bookId;
        this.readerId = readerId;
        this.text = text;
        this.rating = rating;
        this.reviewDate = reviewDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public int getReaderId() {
        return readerId;
    }

    public String getText() {
        return text;
    }

    public int getRating() {
        return rating;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }


    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

}
