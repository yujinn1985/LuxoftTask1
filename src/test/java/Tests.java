import com.gmail.myyujinn.entity.MyFile;
import com.gmail.myyujinn.entity.MyLine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.List;

import static org.junit.Assert.*;

public class Tests {
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/filestatisticsDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    private Connection conn;

    @Before
    public void prepare() {
        try {
            conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void finish() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkDataFileOne() throws SQLException {
        MyFile mf = new MyFile("src/main/resources/New Folder/Folder Two/Chapter One");
        List<MyLine> list = mf.getListOfLines();
        try (Statement st = conn.createStatement()) {
            try (ResultSet rs = st.executeQuery("SELECT * FROM statistics WHERE file = 1")) {
                for (int i = 0; rs.next(); i++) {
                    assertEquals(rs.getInt(2), list.get(i).getLineLength());
                    assertEquals(rs.getString(3), list.get(i).getLongestWord());
                    assertEquals(rs.getString(4), list.get(i).getShortestWord());
                    assertEquals(rs.getDouble(5), list.get(i).getAverageWordLength(), 0);
                }
            }
        }
    }

    @Test
    public void testStatisticCountFileOne() throws SQLException {
        MyFile mf = new MyFile("src/main/resources/New Folder/Folder Two/Chapter One");
        List<MyLine> list = mf.getListOfLines();
        try (Statement st = conn.createStatement()) {
            try (ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM statistics WHERE file = 1")) {
                assertTrue(rs.next());
                assertEquals(rs.getInt(1), list.size());
            }
        }
    }

    @Test
    public void checkDataFileTwo() throws SQLException {
        MyFile mf = new MyFile("src/main/resources/New Folder/Luxoft");
        List<MyLine> list = mf.getListOfLines();
        try (Statement st = conn.createStatement()) {
            try (ResultSet rs = st.executeQuery("SELECT * FROM statistics WHERE file = 2")) {
                for (int i = 0; rs.next(); i++) {
                    assertEquals(rs.getInt(2), list.get(i).getLineLength());
                    assertEquals(rs.getString(3), list.get(i).getLongestWord());
                    assertEquals(rs.getString(4), list.get(i).getShortestWord());
                    assertEquals(rs.getDouble(5), list.get(i).getAverageWordLength(), 0);
                }
            }
        }
    }

    @Test
    public void testStatisticCountFileTwo() throws SQLException {
        MyFile mf = new MyFile("src/main/resources/New Folder/Luxoft");
        List<MyLine> list = mf.getListOfLines();
        try (Statement st = conn.createStatement()) {
            try (ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM statistics WHERE file = 2")) {
                assertTrue(rs.next());
                assertEquals(rs.getInt(1), list.size());
            }
        }
    }

    @Test
    public void checkDataFileThree() throws SQLException {
        MyFile mf = new MyFile("src/main/resources/New Folder/New Folder One/Snippet");
        List<MyLine> list = mf.getListOfLines();
        try (Statement st = conn.createStatement()) {
            try (ResultSet rs = st.executeQuery("SELECT * FROM statistics WHERE file = 3")) {
                for (int i = 0; rs.next(); i++) {
                    assertEquals(rs.getInt(2), list.get(i).getLineLength());
                    assertEquals(rs.getString(3), list.get(i).getLongestWord());
                    assertEquals(rs.getString(4), list.get(i).getShortestWord());
                    assertEquals(rs.getDouble(5), list.get(i).getAverageWordLength(), 0);
                }
            }
        }
    }

    @Test
    public void testStatisticCountFileThree() throws SQLException {
        MyFile mf = new MyFile("src/main/resources/New Folder/New Folder One/Snippet");
        List<MyLine> list = mf.getListOfLines();
        try (Statement st = conn.createStatement()) {
            try (ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM statistics WHERE file = 3")) {
                assertTrue(rs.next());
                assertEquals(rs.getInt(1), list.size());
            }
        }
    }

    @Test
    public void checkDataFileFour() throws SQLException {
        MyFile mf = new MyFile("src/main/resources/New Folder/The Preface");
        List<MyLine> list = mf.getListOfLines();
        try (Statement st = conn.createStatement()) {
            try (ResultSet rs = st.executeQuery("SELECT * FROM statistics WHERE file = 4")) {
                for (int i = 0; rs.next(); i++) {
                    assertEquals(rs.getInt(2), list.get(i).getLineLength());
                    assertEquals(rs.getString(3), list.get(i).getLongestWord());
                    assertEquals(rs.getString(4), list.get(i).getShortestWord());
                    assertEquals(rs.getDouble(5), list.get(i).getAverageWordLength(), 0);
                }
            }
        }
    }

    @Test
    public void testStatisticCountFileFour() throws SQLException {
        MyFile mf = new MyFile("src/main/resources/New Folder/The Preface");
        List<MyLine> list = mf.getListOfLines();
        try (Statement st = conn.createStatement()) {
            try (ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM statistics WHERE file = 4")) {
                assertTrue(rs.next());
                assertEquals(rs.getInt(1), list.size());
            }
        }
    }
}
