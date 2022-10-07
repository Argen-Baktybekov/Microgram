package kg.attractor.microgram.service;

import kg.attractor.microgram.dao.CommentDao;
import kg.attractor.microgram.dto.CommentDto;
import kg.attractor.microgram.dto.PublicationDto;
import kg.attractor.microgram.entity.Comment;
import kg.attractor.microgram.entity.Publication;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Builder
public class CommentService {

    private final CommentDao commentDao;

    public String addComment(int pubId, int userId, String comment){
        try {
            commentDao.addComment(pubId, userId, comment);
            return "Ok";
        }catch (Exception e){
           return e.getMessage();
        }
    }

    public String deleteComment(Integer commentId, int userId) {
        try {
            commentDao.deleteComment(commentId, userId);
            return "Ok";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public List<CommentDto> getComments(int pubId) {
        List<Comment> comments = commentDao.getAllComments(pubId);
        List<CommentDto> commentsDto = new ArrayList<>();
        for (Comment cmt:comments) {
            CommentDto commentDto = makeCommentDto(cmt);
            commentsDto.add(commentDto);
        }
        return commentsDto;
    }

    public static CommentDto makeCommentDto(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setUser(UserService.makeUserDto(comment.getUser()));
        commentDto.setPublication(PublicationService.makePublicationDto(comment.getPublication()));
        commentDto.setText(comment.getText());
        commentDto.setCommentDateTime(comment.getCommentDateTime());
        return commentDto;
    }

}
