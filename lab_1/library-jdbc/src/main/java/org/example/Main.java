package org.example;

import org.example.dao.BookDao;
import org.example.dao.LibraryDao;
import org.example.model.Book;
import org.example.model.Library;

import org.example.db.DatabaseInit;


public class Main {
    public static void main(String[] args) throws Exception {

        DatabaseInit.init();

        LibraryDao libraryDao = new LibraryDao();
        BookDao bookDao = new BookDao();

        libraryDao.save(new Library("Central Library"));

        Library lib = libraryDao.findAll().get(0);

        bookDao.save(new Book("1984", "George Orwell", lib.getId()));
        bookDao.save(new Book("Solaris", "Stanislaw Lem", lib.getId()));

        for (Book b : bookDao.findAll()) {
            System.out.println(b.getTitle());
        }

    }
}
