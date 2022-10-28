package kg.attractor.microgram.dao;

import kg.attractor.microgram.dto.PublicationDto;
import kg.attractor.microgram.entity.Publication;
import kg.attractor.microgram.service.PublicationRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor

public class PublicationDao {
    private final JdbcTemplate jdbcTemplate;
    GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
    public List<Publication> getAllPublication() {
        String query = "select * from publications p\n" +
                "inner join users u on p.user_id = u.id\n";
        return jdbcTemplate.query(query, new PublicationRowMapper());
    }
    public List<Publication> getAllMyPublication(int id) {
        String query = "select * from publications p\n" +
                "inner join users u on p.user_id = u.id\n" +
                "where p.user_id ="+id;
        return jdbcTemplate.query(query, new PublicationRowMapper());
    }
    public Publication getPublicationById(long id) {
        String query = "select * from publications p\n" +
                "inner join users u on p.user_id = u.id\n" +
                "where p.id ="+id;
        return jdbcTemplate.queryForObject(query, new PublicationRowMapper());

    }

    public List<Publication> getPublicationListForMe(int id) {
        String query = "select * from publications p\n" +
                "inner join users u on p.user_id = u.id\n" +
                "where p.user_id in (select subscript_user_id from subscriptions where subscriptions.user_id ="+id+")";
        return jdbcTemplate.query(query, new PublicationRowMapper());
    }


    public Publication addPublication(long userId, String imageLink, String description) throws SQLException {
        String sql ="INSERT INTO publications ( user_id, imagelink, description, date)\n" +
        "    VALUES( ?, ?, ?, ?);";

       var update = jdbcTemplate.update(conn->{
            PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, userId);  //auth
            ps.setString(2, imageLink);
            ps.setString(3, description);
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            return ps;
        }, generatedKeyHolder);
        if (update == 0){
            throw new SQLException();
        }
       long id = Objects.requireNonNull(generatedKeyHolder.getKey().longValue());
        return getPublicationById(id);
    }

    public void deletePublication(int userId, int pubId) throws SQLException{
        String query = "delete from publications where id = ? and user_id=? ;";
        int update = jdbcTemplate.update(query, pubId, userId);

        if (update == 0){
            throw new SQLException();
        }

    }
}
