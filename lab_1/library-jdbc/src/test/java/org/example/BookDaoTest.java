package org.example;

import org.example.dao.BookDao;
import org.example.dao.LibraryDao;
import org.example.dao.StatusDao;
import org.example.db.DatabaseInit;
import org.example.db.DatabaseUtil;
import org.example.model.Book;
import org.example.model.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class BookDaoTest {

    @BeforeEach
    void setUp() throws Exception {
        DatabaseInit.init();

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("DELETE FROM review");
            stmt.executeUpdate("DELETE FROM book");
            stmt.executeUpdate("DELETE FROM reader");
            stmt.executeUpdate("DELETE FROM library");
        }
    }

    @Test
    void saveAndFindAllBooks() throws Exception {
        LibraryDao libraryDao = new LibraryDao();
        BookDao bookDao = new BookDao();
        StatusDao statusDao = new StatusDao();

        Library lib = new Library("Lib");
        libraryDao.save(lib);

        int statusId = statusDao.findAll().get(0).getId();

        Book book = new Book("Title", "Author", lib.getId(), statusId);
        bookDao.save(book);

        List<Book> books = bookDao.findAll();
        assertEquals(1, books.size());
        assertEquals("Title", books.get(0).getTitle());
        assertEquals(statusId, books.get(0).getStatusId());
    }
}
