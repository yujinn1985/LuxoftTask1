package com.gmail.myyujinn.daoclass;

import com.gmail.myyujinn.dao.StatisticDAOMyLine;
import com.gmail.myyujinn.entity.MyFile;
import com.gmail.myyujinn.entity.MyLine;

import java.sql.*;
import java.util.List;

public class MyLineDAO implements StatisticDAOMyLine {

    public MyLineDAO() {

    }

    @Override
    public void addLineStat(Connection conn, MyFile mf) {
        List<MyLine> list = mf.getListOfLines();
        try {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO statistics " +
                    "(line_length, longest_word, shortest_word, averaging_length, file)" +
                    " VALUES(?,?,?,?,?)")) {
                for (MyLine line : list) {
                    ps.setInt(1, line.getLineLength());
                    ps.setString(2, line.getLongestWord());
                    ps.setString(3, line.getShortestWord());
                    ps.setDouble(4, line.getAverageWordLength());
                    ps.setInt(5, mf.getId());
                    ps.executeUpdate();
                }
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
