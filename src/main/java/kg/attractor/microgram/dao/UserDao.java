package kg.attractor.microgram.dao;

import kg.attractor.microgram.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers(){
        String query = "select * from users";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> getUserByEmail(String email){
        String query = String.format("select * from users where email = '%s' ;", email) ;
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class));
    }
    public User getUserByName(String name){
        String query = String.format("select * from users where name = '%s' ;", name) ;
        return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(User.class));
    }
    public User getUserByNickName(String nickName){
        String query = String.format("select * from users where nick_name = '%s' ;", nickName) ;
        return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(User.class));
    }

    public void addUser(User user) {
        String query = "INSERT INTO users (name, nick_name, email, password)\n" +
                "    VALUES( ?, ?, ?, ?); \n";
        jdbcTemplate.update(conn->{
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getNickName());
            ps.setString(3, user.getEmail());
            ps.setString(4, passwordEncoder.encode(user.getPassword()));
            return ps;
        });
        addAuth(user.getEmail());
    }

    public void addAuth(String email){
        String query = "INSERT INTO authorities (username, authority)\n" +
                "    VALUES( ?, ?); \n";
        jdbcTemplate.update(conn->{
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, "ROLE_USER");
            return ps;
        });
    }

    public User authUser(String email, String password) {
        String query = "select * from users where email = ? and password = ?";
       return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(User.class), email, password);
    }
}
