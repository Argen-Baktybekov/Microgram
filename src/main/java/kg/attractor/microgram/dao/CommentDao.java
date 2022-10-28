package kg.attractor.microgram.dao;

import kg.attractor.microgram.dto.CommentDto;
import kg.attractor.microgram.entity.Comment;
import kg.attractor.microgram.entity.Publication;
import kg.attractor.microgram.service.CommentRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
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
    public long addComment(int pubId, int userId, String comment) throws SQLException {
        String query = "insert into comments (user_id, publication_id, text, date )\n"+
                "    VALUES( ?, ?, ?, ?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int update = jdbcTemplate.update(conn->{
            PreparedStatement ps = conn.prepareStatement(query, new String[]{"id"});
            ps.setInt(1, userId);
            ps.setInt(2, pubId);
            ps.setString(3, comment);
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            return ps;
        }, keyHolder);
        if (update == 0){
            throw new SQLException();
        }
        long id = keyHolder.getKey().longValue();
        return id;
    }

    public void deleteComment (int commentId, int userId) throws SQLException{
        String query = "delete from comments where id = ? and publication_id in " +
                "(select id from publications where user_id = ? );";
       int update = jdbcTemplate.update(query, commentId, userId);
        if (update == 0){
            throw new SQLException();
        }
    }

    public List<CommentDto> getAllComments(int pubId) {
        String query = "select * from comments c \n " +
                "inner join users u on c.user_id = u.id\n" +
                "inner join publications p on c.publication_id = p.id\n" +
                "inner join users us on p.user_id = us.id\n" +
                "where p.id = ? ;";

       return jdbcTemplate.query(query, new CommentRowMapper(), pubId);
    }
}
