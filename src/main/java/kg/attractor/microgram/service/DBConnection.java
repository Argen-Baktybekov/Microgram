package kg.attractor.microgram.service;

import com.sun.source.doctree.SeeTree;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Service
public class DBConnection {
    @Getter
    private Connection conn;

    public DBConnection() {
        try {
            init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getNewConnection() throws SQLException {
        String url = "jdbc:postgresql://192.168.99.100:5432/postgres?user=postgres&password=qwerty";
        return DriverManager.getConnection(url);
    }

    private void init() throws SQLException {
        conn = getNewConnection();
    }

    private void close() throws SQLException {
        conn.close();
    }

    public String openConnection() {
        try {
            init();
            return "Connection to database successful";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
}
