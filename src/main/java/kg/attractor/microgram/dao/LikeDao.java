package kg.attractor.microgram.dao;

import kg.attractor.microgram.entity.Like;
import kg.attractor.microgram.entity.Publication;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LikeDao {

    private final JdbcTemplate jdbcTemplate;

    public List<Like> getLike(int userId, int publicationId) {
        String query = "select * from likes where user_id ="+userId+" and publication_id ="+publicationId;
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Like.class));
    }

}
