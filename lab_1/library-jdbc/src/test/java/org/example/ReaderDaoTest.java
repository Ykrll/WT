package org.example;

import org.example.dao.ReaderDao;
import org.example.db.DatabaseInit;
import org.example.model.Reader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ReaderDaoTest {

    @BeforeEach
    void setUp() throws Exception {
        DatabaseInit.init();
    }

    @Test
    void saveAndFindAllReaders() throws Exception {
        ReaderDao dao = new ReaderDao();

        dao.save(new Reader("Ivan"));

        List<Reader> readers = dao.findAll();
        assertEquals(1, readers.size());
        assertEquals("Ivan", readers.get(0).getName());
    }
}
