//package kg.attractor.microgram.controller;
//
//import kg.attractor.microgram.service.LikeService;
//import kg.attractor.microgram.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/like")
//public class LikeController {
//    private final LikeService service;
//    private final UserService userService;
//
//
//    @GetMapping("/{userId:\\d+}/{publicationId:\\d+}")
//    public ResponseEntity<Boolean> isLike(@PathVariable int userId, @PathVariable int publicationId){
//        return new ResponseEntity<>(service.isLiked(userId, publicationId), HttpStatus.OK);
//    }
//    @PostMapping("/{pubId}")
//    public ResponseEntity<String> add(@PathVariable Integer pubId, Authentication authentication){
//        String email = authentication.getName();
//        int userId = userService.getUsersByEmail(email).getId();
//        return new ResponseEntity<>(service.addLike(userId, pubId), HttpStatus.OK);
//    }
//
//
//}
