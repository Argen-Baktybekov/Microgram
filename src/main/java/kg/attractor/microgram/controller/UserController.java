package kg.attractor.microgram.controller;

import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.entity.Comment;
import kg.attractor.microgram.entity.User;
import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class UserController {
private final UserService service;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> users() {
        return new ResponseEntity<>(service.getUsers(), HttpStatus.OK);
    }
    @GetMapping("/user/{email}")
    public ResponseEntity<UserDto> userByEmail(@PathVariable String email){
        return new ResponseEntity<>(service.getUsersByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/user/name/{name}")
    public ResponseEntity<UserDto> userByName(@PathVariable String name){
        return new ResponseEntity<>(service.getUsersByName(name), HttpStatus.OK);
    }

    @GetMapping("/user/nickname/{nickName}")
    public ResponseEntity<UserDto> userByNickName(@PathVariable String nickName){
        return new ResponseEntity<>(service.getUsersByNickName(nickName), HttpStatus.OK);
    }

    @GetMapping("/user/check/{email}")
    public ResponseEntity<Boolean> isUserByEmail(@PathVariable String email){
        return new ResponseEntity<Boolean>(service.isUsersByEmail(email), HttpStatus.OK);
    }

    @PostMapping("/user/add")
    public ResponseEntity<String> addUser(@RequestBody User user){
        return new ResponseEntity<>(service.addUser(user), HttpStatus.OK);
    }

    @PostMapping("/user/auth")
    public ResponseEntity<String> authUser(@RequestParam String email,@RequestParam String password){
        return new ResponseEntity<>(service.authUser(email, password), HttpStatus.OK);
    }
}
