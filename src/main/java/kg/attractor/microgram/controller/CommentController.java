package kg.attractor.microgram.controller;

import kg.attractor.microgram.dto.CommentDto;
import kg.attractor.microgram.dto.PublicationDto;
import kg.attractor.microgram.service.CommentService;
import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService service;
    private final UserService userService;

    @GetMapping("/{pubId}")

    // нет кастомного RowMapper

    public ResponseEntity<List<CommentDto>> getCommentsForPublication(@PathVariable Integer pubId){
        return new ResponseEntity<>(service.getComments(pubId), HttpStatus.OK);
    }

    @PostMapping("/{pubId}")
    public ResponseEntity<String> addComment(@PathVariable Integer pubId,
                                             Authentication authentication,
                                             @RequestParam String text) {
        String email = authentication.getName();
        if (text != null) {
            int userId = userService.getUsersByEmail(email).getId();
            return new ResponseEntity<>(service.addComment(pubId, userId, text), HttpStatus.OK);
        }
        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Integer commentId,
                                                Authentication authentication) {
        String email = authentication.getName();
        int userId = userService.getUsersByEmail(email).getId();
        return new ResponseEntity<>(service.deleteComment(commentId, userId), HttpStatus.OK);
    }

}
