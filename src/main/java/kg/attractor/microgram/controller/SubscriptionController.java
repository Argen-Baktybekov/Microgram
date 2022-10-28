//package kg.attractor.microgram.controller;
//
//import kg.attractor.microgram.service.SubscriptionService;
//import kg.attractor.microgram.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/subscriptions")
//public class SubscriptionController {
//    private final SubscriptionService service;
//    private final UserService userService;
//
//    @PostMapping("/add")
//    public ResponseEntity<String> subscript(@PathVariable int userId,
//                                         Authentication authentication){
//        String email = authentication.getName();
//        int myId = userService.getUsersByEmail(email).getId();
//
//        return new ResponseEntity<>(service.subscript(myId, userId), HttpStatus.OK);
//    }
//    @DeleteMapping("/delete")
//    public ResponseEntity<String> deleteSubscription(@PathVariable int userId,
//                                         Authentication authentication){
//        String email = authentication.getName();
//        int myId = userService.getUsersByEmail(email).getId();
//        return new ResponseEntity<>(service.deleteSubscription(myId, userId), HttpStatus.OK);
//    }
//}
