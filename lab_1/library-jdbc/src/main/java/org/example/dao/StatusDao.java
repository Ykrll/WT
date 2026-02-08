package org.example.dao;

import org.example.db.DatabaseUtil;
import org.example.model.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class StatusDao {

    public List<Status> findAll() throws Exception {
        List<Status> statuses = new ArrayList<>();

        String sql = "SELECT id, name FROM status";

        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Status s = new Status(
                    rs.getInt("id"),
                    rs.getString("name")
                );
                statuses.add(s);
            }
        }

        return statuses;
    }
}
