package com.gmail.myyujinn.daoclass;

import com.gmail.myyujinn.dao.StatisticDAOMyFile;
import com.gmail.myyujinn.entity.MyFile;
import com.gmail.myyujinn.entity.MyLine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MyFileDAO implements StatisticDAOMyFile {

    public MyFileDAO() {
        super();
    }

    @Override
    public void addFileStat(Connection conn, MyFile mf) {
        List<MyLine> list = mf.getListOfLines();
        try {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO file_statistics " +
                    "(total_lines, total_words) VALUES(?,?)")) {
                    ps.setInt(1, list.size());
                    ps.setLong(2, mf.getTotalWords());
                    ps.executeUpdate();
                conn.commit();
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
