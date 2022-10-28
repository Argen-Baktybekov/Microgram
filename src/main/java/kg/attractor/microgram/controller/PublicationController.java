package kg.attractor.microgram.controller;

import kg.attractor.microgram.dto.PublicationDto;
import kg.attractor.microgram.service.PublicationService;
//import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/publications")
public class PublicationController {
private final PublicationService service;
//private final UserService userService;




    @GetMapping("/{id}")
    public ResponseEntity<List<PublicationDto>> singleUserPublications(@PathVariable String id) {
        int userId= Integer.parseInt(id);
        return new ResponseEntity<>(service.getAllMyPublication(userId), HttpStatus.OK);
    }@GetMapping("/")
    public ResponseEntity<List<PublicationDto>> allPublications() {

        return new ResponseEntity<>(service.getAllPublication(), HttpStatus.OK);
    }
//    @GetMapping("/forMe")
//    public ResponseEntity<List<PublicationDto>> publicationsForMe(Authentication authentication){
////        String email = authentication.getName();
////        int userId = userService.getUsersByEmail(email).getId();
//        return new ResponseEntity<>(service.getPublicationListForMe(userId), HttpStatus.OK);
//    }
    @GetMapping("/my")
    public ResponseEntity<List<PublicationDto>> myPublications(int authentication){
//        String email = authentication.getName();
//        int userId = userService.getUsersByEmail(email).getId();
        return new ResponseEntity<>(service.getAllMyPublication(authentication), HttpStatus.OK);
    }

    @PostMapping(value = "/add",consumes = {"multipart/form-data"})
    public ResponseEntity<PublicationDto> addPublication(@RequestPart("postfile") MultipartFile file,
                                                 @RequestParam String description,
                                                 @RequestParam Long userId
                                                 ){
//        String email = authentication.getName();
//        if (email != null){
////        int userId = userService.getUsersByEmail(email).getId();

        return new  ResponseEntity<>(service.addPublication(userId, file, description), HttpStatus.OK);
    }
//    @DeleteMapping("/{pubId}")
//    public ResponseEntity<String> deletePublication(@PathVariable int pubId,
//    Authentication authentication){
//        String email = authentication.getName();
//        int userId = userService.getUsersByEmail(email).getId();
//        return new ResponseEntity<>(service.deletePublication(userId, pubId), HttpStatus.OK);
//    }
}
