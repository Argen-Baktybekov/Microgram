package kg.attractor.microgram.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class SubscriptionDao {
    private final JdbcTemplate jdbcTemplate;
    public void add(int myId, int userId) throws SQLException{
        String query ="INSERT INTO subscriptions ( user_id, subscript_user_id, date)\n" +
                "    VALUES( ?, ?, ?);";
       int update = jdbcTemplate.update(conn->{
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, myId);  //auth
            ps.setInt(2, userId);
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            return ps;
        });
        if (update == 0){
            throw new SQLException();
        }
    }

    public void deleteSubscriptions(int myId, int userId) throws SQLException {
        String query = "delete from subscriptions where user_id = ? and subscript_user_id = ? ;";
        int update = jdbcTemplate.update(query,myId, userId);
        if (update == 0){
            throw new SQLException();
        }
    }
}
