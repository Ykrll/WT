package org.example;

import org.example.dao.BookDao;
import org.example.model.Book;


public class Main {
    public static void main(String[] args) throws Exception {

        BookDao bookDao = new BookDao();

        bookDao.save(new Book("title1", "author1"));
        bookDao.save(new Book("title2", "author2"));

        for (Book b : bookDao.findAll()) {
            System.out.println(b.getTitle() + " - " + b.getAuthor());
        }

    }
}
