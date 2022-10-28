package kg.attractor.microgram.controller;

import kg.attractor.microgram.service.DBConnection;
//import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ConnectionController {
    private final DBConnection dbConnection;
//    final UserService dBservice;

    @GetMapping("/create")
    public ResponseEntity<String> createTable(){
        return new ResponseEntity<>(dbConnection.createNewDB(), HttpStatus.OK);
    }
    @GetMapping("/addtestdata")
    public ResponseEntity<String> addTestData(){
        return new ResponseEntity<>(dbConnection.addTestData(), HttpStatus.OK);
    }



}
