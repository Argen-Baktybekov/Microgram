package kg.attractor.microgram.microgram;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Comment {
    private int id;
    private String text;
    private LocalDateTime CommentDateTime;

    public Comment(int id, String text, LocalDateTime commentDateTime) {
        this.id = id;
        this.text = text;
        CommentDateTime = commentDateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCommentDateTime() {
        return CommentDateTime;
    }

    public void setCommentDateTime(LocalDateTime commentDateTime) {
        CommentDateTime = commentDateTime;
    }
}

