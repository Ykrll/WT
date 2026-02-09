package org.example;

import org.example.dao.StatusDao;
import org.example.db.DatabaseInit;
import org.example.model.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class StatusDaoTest {

    @BeforeEach
    void setUp() throws Exception {
        DatabaseInit.init();
    }

    @Test
    void findAllStatuses() throws Exception {
        StatusDao dao = new StatusDao();

        List<Status> statuses = dao.findAll();
        assertEquals(2, statuses.size());
    }
}
