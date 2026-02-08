package org.example.model;


public class Book {

    private int id;
    private String title;
    private String author;
    private int libraryId;
    private Integer statusId;

    public Book() {}

    public Book(String title, String author, int libraryId) {
        this.title = title;
        this.author = author;
        this.libraryId = libraryId;
    }

    public Book(String title, String author, int libraryId, Integer statusId) {
        this.title = title;
        this.author = author;
        this.libraryId = libraryId;
        this.statusId = statusId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getLibraryId() {
        return libraryId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }
}
