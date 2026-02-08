package org.example.model;


public class Reader {
    private int id;
    private String name;

    public Reader() {}

    public Reader(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
}
