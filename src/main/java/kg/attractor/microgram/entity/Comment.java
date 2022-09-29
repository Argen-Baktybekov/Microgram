package kg.attractor.microgram.entity;

import lombok.Data;

import java.security.PrivateKey;
import java.time.LocalDateTime;
@Data
public class Comment {
    private int id;
    private User user;
    private Publication publication;
    private String text;
    private LocalDateTime CommentDateTime;

    public Comment(int id, User user, Publication publication, String text, LocalDateTime commentDateTime) {
        this.id = id;
        this.user = user;
        this.publication = publication;
        this.text = text;
        CommentDateTime = commentDateTime;
    }

}

