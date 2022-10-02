package kg.attractor.microgram.dao;

import kg.attractor.microgram.entity.Publication;
import kg.attractor.microgram.service.PublicationRowMapper;
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
        String query = "select * from publications p\n" +
                "inner join users u on p.user_id = u.id\n" +
                "where p.user_id !="+id;
        return jdbcTemplate.query(query, new PublicationRowMapper());
    }

    public List<Publication> getMyListPublication(int id) {
        String query = "select * from publications p\n" +
                "inner join users u on p.user_id = u.id\n" +
                "where p.user_id ="+id;
        return jdbcTemplate.query(query, new PublicationRowMapper());
    }

    public List<Publication> getPublicationListForMe(int id) {
        String query = "select * from publications p\n" +
                "inner join users u on p.user_id = u.id\n" +
                "where p.user_id in (select subscript_user_id from subscriptions where subscriptions.user_id ="+id+")";
        return jdbcTemplate.query(query, new PublicationRowMapper());
    }


}
