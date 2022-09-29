package kg.attractor.microgram.controller;


import kg.attractor.microgram.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class LikeController {
    private final LikeService service;


    @GetMapping("/like/{userId:\\d+}/{publicationId:\\d+}")
    public ResponseEntity<Boolean> isLike(@PathVariable int userId, @PathVariable int publicationId){
//        int likeId = Integer.parseInt(id);
        return new ResponseEntity<Boolean>(service.isLiked(userId, publicationId), HttpStatus.OK);
    }
}
