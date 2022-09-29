package kg.attractor.microgram.service;

import kg.attractor.microgram.dao.CreateDB;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class DBConnection {
    private final CreateDB createDB;

    @Getter
    private Connection conn;

    public DBConnection() {
        try {
            init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        createDB = null;
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

    public String createNewDB(){
        try {
            createDB.creataDB();
            return "OK";
        }catch (Exception e){
          return e.getMessage();
        }
    }
    public String addTestData(){
        try {
            createDB.addTestDataDB();
            return "OK";
        }catch (Exception e){
          return e.getMessage();
        }
    }
}
