package kg.attractor.microgram.dao;

import kg.attractor.microgram.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CommentDao {
    private final JdbcTemplate jdbcTemplate;
    public void addComment(Comment comment) {
        String query = "insert into comments (user_id, publication_id, text, date )\n"+
                "    VALUES( ?, ?, ?, ?);";
        jdbcTemplate.update(conn->{
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, comment.getUser().getId());
            ps.setInt(2, comment.getPublication().getId());
            ps.setString(3, comment.getText());
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            return ps;
        });
    }

    public void deleteComment (Comment comment){

    }
}
