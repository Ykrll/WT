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

        System.out.println("LIBRARY\n========");


        Library library = new Library("Central Library");
        libraryDao.save(library);
        System.out.println("Created library: " + library.getName());


        List<Library> libraries = libraryDao.findAll();
        System.out.println("All libraries:");
        for (Library l : libraries) {
            System.out.println("  " + l.getId() + " | " + l.getName());
        }


        int availableId = -1;

        for (Status s : statusDao.findAll()) {
            if ("AVAILABLE".equals(s.getName())) {
                availableId = s.getId();
            }
        }


        System.out.println("\nBOOK\n=====");

        Book book1 = new Book("Evgeni Onegin", "Pushkin Sanya", library.getId(), availableId);
        Book book2 = new Book("Voina i mir", "Lev Tolstui", library.getId(), availableId);
        Book book3 = new Book("Ford Galaxy user manual", "Ymnui Chel", library.getId(), availableId);
        bookDao.save(book1);
        bookDao.save(book2);
        bookDao.save(book3);


        List<Book> books = bookDao.findAll();
        for (Book b : books) {
            System.out.println("Book: " + b.getTitle() + " | " + b.getAuthor() + " | statusId=" + b.getStatusId());
        }

        System.out.println("\nREADER\n========");

        Reader reader = new Reader("Ilon Max");
        readerDao.save(reader);

        for (Reader r : readerDao.findAll()) {
            System.out.println("Reader: " + r.getName());
        }


        System.out.println("\nREVIEW (sorted by date)\n========================");

        reviewDao.save(new Review(
                book1.getId(),
                reader.getId(),
                "Great book",
                4,
                LocalDate.now().minusDays(10)
        ));

        reviewDao.save(new Review(
                book2.getId(),
                reader.getId(),
                "Hard but interesting",
                3,
                LocalDate.now().minusMonths(3)
        ));

        reviewDao.save(new Review(
                book3.getId(),
                reader.getId(),
                "Fcng awesome!",
                5,
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

    }
}
