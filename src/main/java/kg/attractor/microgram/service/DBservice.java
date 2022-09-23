package kg.attractor.microgram.service;

import kg.attractor.microgram.dao.UserDao;
import kg.attractor.microgram.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DBservice {
    private final DBConnection dbService;
    private final UserDao userDao;
    private int executeUpdate(String query) throws SQLException {
        Statement statement = dbService.getConn().createStatement();
        return statement.executeUpdate(query);
    }

    private void createUserTable() throws SQLException {
        String createTableqQuery= "CREATE TABLE users( "+"id INTEGER PRIMARY KEY, "+"name VARCHAR(45), "+
                "email VARCHAR(45), "+ "password VARCHAR(45), "+"publicCount INTEGER, "
                +"subscriptionsCount INTEGER, "+ "subscribersCount INTEGER )";

        String insertQuery = "INSERT INTO users "+
                "VALUES(10, 'Brian', 'br@gmail.com', 'qwerty', 0, 0,0)";

        executeUpdate(createTableqQuery);
        executeUpdate(insertQuery);
    }
    public String shouldCreateTable(){
        try {
            createUserTable();
            dbService.getConn().createStatement().execute("select * from users");
            return "All it's OK";
        }catch (SQLException e){
           return e.getMessage();
        }
    }



    public List<User> getUsers(){
        return userDao.getAllUsers();
    }
}
