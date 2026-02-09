package org.example;

import org.example.dao.LibraryDao;
import org.example.db.DatabaseInit;
import org.example.model.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class LibraryDaoTest {

    private LibraryDao libraryDao;

    @BeforeEach
    void setUp() throws Exception {
        DatabaseInit.init();
        libraryDao = new LibraryDao();
    }

    @Test
    void saveAndFindAll() throws Exception {
        Library lib = new Library("Test Library");
        libraryDao.save(lib);

        assertTrue(lib.getId() > 0);

        List<Library> libraries = libraryDao.findAll();
        assertEquals(1, libraries.size());
        assertEquals("Test Library", libraries.get(0).getName());
    }
}
