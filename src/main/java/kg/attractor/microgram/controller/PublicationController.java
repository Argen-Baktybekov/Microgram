package kg.attractor.microgram.controller;

import kg.attractor.microgram.dto.PublicationDto;
import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.service.PublicationService;
import kg.attractor.microgram.service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.apache.coyote.http11.Constants.a;

@RestController
@RequiredArgsConstructor
@RequestMapping("/publications")
public class PublicationController {
private final PublicationService service;
private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<List<PublicationDto>> publications(@PathVariable String id) {
        int userId= Integer.parseInt(id);
        return new ResponseEntity<>(service.getAllPublication(userId), HttpStatus.OK);
    }
    @GetMapping("/forMe/{id}")
    public ResponseEntity<List<PublicationDto>> publicationsForMe(@PathVariable String id){
        int userId= Integer.parseInt(id);
        return new ResponseEntity<>(service.getPublicationListForMe(userId), HttpStatus.OK);
    }
    @GetMapping("/my/{id}")
    public ResponseEntity<List<PublicationDto>> myPublications(@PathVariable String id){
        int userId= Integer.parseInt(id);
        return new ResponseEntity<>(service.getMyListPublication(userId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addPublication(@RequestBody PublicationDto publication){
        return new  ResponseEntity<>(service.addPuplication(publication), HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePublication(@RequestParam int pubId,
    Authentication authentication){
        String email = authentication.getName();
        int userId = userService.getUsersByEmail(email).getId();
        return new ResponseEntity<>(service.deletePublication(userId, pubId), HttpStatus.OK);
    }
}
