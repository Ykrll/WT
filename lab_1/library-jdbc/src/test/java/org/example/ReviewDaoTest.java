package org.example;

import org.example.dao.BookDao;
import org.example.dao.LibraryDao;
import org.example.dao.StatusDao;
import org.example.dao.ReaderDao;
import org.example.dao.ReviewDao;
import org.example.db.DatabaseInit;
import org.example.model.Book;
import org.example.model.Reader;
import org.example.model.Review;
import org.example.model.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ReviewDaoTest {

    @BeforeEach
    void setUp() throws Exception {
        DatabaseInit.init();
    }

    @Test
    void saveAndFindSortedReviews() throws Exception {
        LibraryDao libraryDao = new LibraryDao();
        BookDao bookDao = new BookDao();
        ReaderDao readerDao = new ReaderDao();
        StatusDao statusDao = new StatusDao();
        ReviewDao reviewDao = new ReviewDao();

        Library lib = new Library("Lib");
        libraryDao.save(lib);

        int statusId = statusDao.findAll().get(0).getId();

        Book book = new Book("Book", "Author", lib.getId(), statusId);
        bookDao.save(book);

        Reader reader = new Reader("User");
        readerDao.save(reader);

        reviewDao.save(new Review(
                book.getId(), reader.getId(),
                "Old", 3, LocalDate.now().minusDays(1)
        ));

        reviewDao.save(new Review(
                book.getId(), reader.getId(),
                "New", 5, LocalDate.now()
        ));

        List<Review> reviews = reviewDao.findAllSortedByDate();
        assertEquals(2, reviews.size());
        assertEquals("New", reviews.get(0).getText());
    }
}
