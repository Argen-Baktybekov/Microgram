package kg.attractor.microgram.dao;

import kg.attractor.microgram.entity.Like;
import kg.attractor.microgram.entity.Publication;
import lombok.RequiredArgsConstructor;
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
public class LikeDao {

    private final JdbcTemplate jdbcTemplate;

    public Boolean getLike(int userId, int publicationId) {
        String query = "select count(*) from likes where user_id ="+userId+" and publication_id ="+publicationId;
        Integer cnt =jdbcTemplate.queryForObject(query, Integer.class);
        return cnt!=null && cnt>0;
    }

    public void addLike(int userId, Integer pubId) throws SQLException {
        String query = "insert into likes (user_id, publication_id, date) \n"+
                "    VALUES( ?, ?, ?);";
       int update = jdbcTemplate.update(conn-> {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, pubId);
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            return ps;
        });
        if (update == 0){
            throw new SQLException();
        }
    }

}
