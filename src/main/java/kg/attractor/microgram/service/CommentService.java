package kg.attractor.microgram.service;

import kg.attractor.microgram.dao.CommentDao;
import kg.attractor.microgram.entity.Comment;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Builder
public class CommentService {

    private final CommentDao commentDao;

    public String addComment(Comment comment){
        try {
            commentDao.addComment(comment);
            return "Ok";
        }catch (Exception e){
           return e.getMessage();
        }
    }
}
