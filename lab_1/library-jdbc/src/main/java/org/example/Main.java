package org.example;

import org.example.dao.*;
import org.example.db.DatabaseInit;
import org.example.model.*;

import java.time.LocalDate;
import java.util.List;


public class Main {

    public static void main(String[] args) throws Exception {

        DatabaseInit.init();

        LibraryDao libraryDao = new LibraryDao();
        BookDao bookDao = new BookDao();
        ReaderDao readerDao = new ReaderDao();
        StatusDao statusDao = new StatusDao();
        ReviewDao reviewDao = new ReviewDao();

        System.out.println("=== LIBRARY ===");


        Library library = new Library("Central Library");
        libraryDao.save(library);
        System.out.println("Created library: " + library.getName());


        List<Library> libraries = libraryDao.findAll();
        System.out.println("All libraries:");
        for (Library l : libraries) {
            System.out.println(" - " + l.getId() + " | " + l.getName());
        }

        System.out.println("\n=== BOOK ===");


        Book book1 = new Book("1984", "George Orwell", library.getId());
        Book book2 = new Book("Solaris", "Stanislaw Lem", library.getId());
        bookDao.save(book1);
        bookDao.save(book2);


        List<Book> books = bookDao.findAll();
        for (Book b : books) {
            System.out.println("Book: " + b.getTitle() + " | " + b.getAuthor());
        }

        System.out.println("\n=== READER ===");

        Reader reader = new Reader("Ivan Ivanov");
        readerDao.save(reader);

        for (Reader r : readerDao.findAll()) {
            System.out.println("Reader: " + r.getName());
        }

        System.out.println("\n=== STATUS (reference table) ===");

        for (Status s : statusDao.findAll()) {
            System.out.println("Status: " + s.getName());
        }

        System.out.println("\n=== REVIEW (sorted by date DESC) ===");


        reviewDao.save(new Review(
                book1.getId(),
                reader.getId(),
                "Great book",
                5,
                LocalDate.now().minusDays(1)
        ));

        reviewDao.save(new Review(
                book2.getId(),
                reader.getId(),
                "Hard but interesting",
                4,
                LocalDate.now()
        ));


        for (Review r : reviewDao.findAllSortedByDate()) {
            System.out.println(
                    "Review: bookId=" + r.getBookId() +
                    ", rating=" + r.getRating() +
                    ", date=" + r.getReviewDate() +
                    ", text=" + r.getText()
            );
        }

        System.out.println("\n=== DONE ===");
    }
}
