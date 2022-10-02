package kg.attractor.microgram.controller;

import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Controller
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
}
