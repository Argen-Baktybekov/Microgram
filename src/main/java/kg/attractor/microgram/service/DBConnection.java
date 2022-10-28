package kg.attractor.microgram.service;

//import kg.attractor.microgram.dao.CreateDB;
import kg.attractor.microgram.dao.CreateDB;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
@RequiredArgsConstructor
//@AllArgsConstructor


public class DBConnection {
    private final CreateDB createDB;

    public String createNewDB(){
        try {
            createDB.createDB();
            return "OK";
        }catch (Exception e){
          return "Data Base already exist";
        }
    }
    public String addTestData(){
        try {
            createDB.addTestDataDB();
            return "OK";
        }catch (Exception e){
          return "Data Base already exist";
        }
    }
}
