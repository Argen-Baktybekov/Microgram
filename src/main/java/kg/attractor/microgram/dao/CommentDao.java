package kg.attractor.microgram.dao;

import kg.attractor.microgram.entity.Comment;
import kg.attractor.microgram.entity.Publication;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentDao {
    private final JdbcTemplate jdbcTemplate;
    public void addComment(int pubId, int userId, String comment) throws SQLException {
        String query = "insert into comments (user_id, publication_id, text, date )\n"+
                "    VALUES( ?, ?, ?, ?);";
        int update = jdbcTemplate.update(conn->{
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, pubId);
            ps.setString(3, comment);
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            return ps;
        });
        if (update == 0){
            throw new SQLException();
        }
    }

    public void deleteComment (int commentId, int userId) throws SQLException{
        String query = "delete from comments where id = ? and publication_id in " +
                "(select id from publications where user_id = ? );";
       int update = jdbcTemplate.update(query, commentId, userId);
        if (update == 0){
            throw new SQLException();
        }
    }

    public List<Comment> getAllComments(int pubId) {
        String query = "select * from comments where publication_id = ? ;";
        // NEED CUSTOM RowMapper
       return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Comment.class), pubId);
    }
}
