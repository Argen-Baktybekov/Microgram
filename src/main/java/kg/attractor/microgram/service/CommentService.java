package kg.attractor.microgram.service;

import kg.attractor.microgram.dao.CommentDao;
import kg.attractor.microgram.dao.PublicationDao;
import kg.attractor.microgram.dao.UserDao;
import kg.attractor.microgram.dto.CommentDto;
import kg.attractor.microgram.entity.Comment;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Builder
public class CommentService {

    private final CommentDao commentDao;
    private final UserDao userDao;
    private final UserService userService;
    private final PublicationDao publicationDao;

    public CommentDto addComment(int pubId, int userId, String comment){
        try {
           long id = commentDao.addComment(pubId, userId, comment);
            return CommentDto.builder()
                    .id(id)
                    .user(UserService.makeUserDto( userDao.getUserById(userId) ) )
                    .publication(PublicationService.makePublicationDto(publicationDao.getPublicationById(pubId)))
                    .text(comment)
                    .dateTime(Timestamp.valueOf(LocalDateTime.now()))
                    .build();

        }catch (Exception e){
            e.getMessage();
        }
        return null;
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
//        commentDto.setUser(UserService.makeUserDto(comment.getUser()));
        commentDto.setPublication(PublicationService.makePublicationDto(comment.getPublication()));
        commentDto.setText(comment.getText());
        commentDto.setDateTime(Timestamp.valueOf(comment.getCommentDateTime()));
        return commentDto;
    }

}
