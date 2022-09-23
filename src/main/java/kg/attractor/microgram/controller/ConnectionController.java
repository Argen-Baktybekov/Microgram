package kg.attractor.microgram.controller;

import kg.attractor.microgram.entity.User;
import kg.attractor.microgram.service.DBConnection;
import kg.attractor.microgram.service.DBservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/51")
@RequiredArgsConstructor
public class ConnectionController {
    private final DBConnection dbConnection;
    final DBservice dBservice;

    @GetMapping("/connect")
    public ResponseEntity<String> getConnection(){
    return new  ResponseEntity<>(dbConnection.openConnection(), HttpStatus.OK);
    }
    @GetMapping("/create")
    public ResponseEntity<String> createTable(){
        return new ResponseEntity<>(dBservice.shouldCreateTable(), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> users() {
        return new ResponseEntity<>(dBservice.getUsers(), HttpStatus.OK);
    }
}
