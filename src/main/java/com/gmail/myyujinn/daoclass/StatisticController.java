package com.gmail.myyujinn.daoclass;

import com.gmail.myyujinn.dao.Controller;
import com.gmail.myyujinn.dao.StatisticDAOMyFile;
import com.gmail.myyujinn.dao.StatisticDAOMyLine;
import com.gmail.myyujinn.entity.MyFile;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StatisticController implements Controller {
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/filestatisticsDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    private Connection conn;

    public StatisticController() {
        try {
            this.conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createTable() {
        try (Statement st = conn.createStatement()) {
            System.out.println("Creating tables in the database...");
            st.execute("DROP TABLE IF EXISTS statistics");
            st.execute("DROP TABLE IF EXISTS file_statistics");
            st.execute("CREATE TABLE file_statistics (" +
                    "file_num INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "total_lines INT NOT NULL, " +
                    "total_words INT NOT NULL" +
                    ")");
            st.execute("CREATE TABLE statistics (" +
                    "stat_num INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "line_length INT NOT NULL, " +
                    "longest_word VARCHAR (100) NOT NULL, " +
                    "shortest_word VARCHAR (100) NOT NULL, " +
                    "averaging_length DOUBLE NOT NULL, " +
                    "file INT DEFAULT NULL, " +
                    "FOREIGN KEY (file) REFERENCES file_statistics (file_num) ON DELETE CASCADE" +
                    ")");
            System.out.println("The tables have been created.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void addStatistic(String path) {
        File file = new File(path);
        if (file.isFile()) {
            MyFile myFile = new MyFile(path);
            StatisticDAOMyFile mfDAO = new MyFileDAO();
            mfDAO.addFileStat(conn, myFile);
            StatisticDAOMyLine mlDAO = new MyLineDAO();
            mlDAO.addLineStat(conn, myFile);
            System.out.println("Statistics has been successfully added to the database.");
        } else {
            File[] files = file.listFiles();
            if (files != null) {
                for (File fileVar : files) {
                    this.addStatistic(fileVar.getPath());
                }
            }
        }
    }

    @Override
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
