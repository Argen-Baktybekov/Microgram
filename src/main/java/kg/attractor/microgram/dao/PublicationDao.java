package kg.attractor.microgram.dao;

import kg.attractor.microgram.entity.Publication;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PublicationDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Publication> getAllPublication(int id) {
        String query = "select * from publications where users.id!="+id;
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Publication.class));
    }

    public List<Publication> getMyListPublication(int id) {
        String query = "select * from publications where users.id="+id;
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Publication.class));
    }

    public List<Publication> getPublicationListForMe(int id) {
        String query = "select * from publications where user_id in (\n" +
                "            select subscript_user_id from subscriptions where subscriptions.user_id ="+id;
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Publication.class));
    }


}
