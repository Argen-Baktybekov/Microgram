package kg.attractor.microgram.dao;

import kg.attractor.microgram.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public List<User> getAllUsers(){
        String query = "select * from users";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> getUserByEmail(String email){
        String query = String.format("select * from users where email = '%s' ", email) ;
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class));
    }
    public List<User> getUserByName(String nickName){
        String query = String.format("select * from users where email = '%s' ", nickName) ;
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class));
    }

}
