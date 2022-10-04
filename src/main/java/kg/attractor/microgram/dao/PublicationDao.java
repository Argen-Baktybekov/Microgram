package kg.attractor.microgram.dao;

import kg.attractor.microgram.dto.PublicationDto;
import kg.attractor.microgram.entity.Publication;
import kg.attractor.microgram.service.PublicationRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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


    public void addPublication(PublicationDto publicationDto) {
        String query ="INSERT INTO users ( user_id, imagelink, description, date)\n" +
        "    VALUES( ?, ?, ?, ?);";
        jdbcTemplate.update(conn->{
            PreparedStatement ps = conn.prepareStatement(query);
//            ps.setInt(1, "user_id");  //auth
            ps.setString(2, publicationDto.getImageLink());
            ps.setString(3, publicationDto.getDescription());
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            return ps;
        });
    }

    public void deletePublication(int userId, int pubId) {
        String query = "delete from publications where id = ? and user_id=? ;";
        jdbcTemplate.update(query,pubId, userId);
    }
}
