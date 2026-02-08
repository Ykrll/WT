package org.example.model;


public class Library {

    private int id;
    private String name;

    public Library() {}

    public Library(String name) {
        this.name = name;
    }

    public Library(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
