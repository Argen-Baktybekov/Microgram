package kg.attractor.microgram.service;

import kg.attractor.microgram.entity.Publication;
import kg.attractor.microgram.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PublicationRowMapper implements RowMapper<Publication> {

    @Override
    public Publication mapRow(ResultSet rs, int rowNum) throws SQLException {
        Publication publication = new Publication();
        publication.setId(rs.getInt("id"));
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setName(rs.getString("name"));
        user.setNickName(rs.getString("nick_name"));
        user.setPublicCount(rs.getInt("public_count"));
        user.setSubscriptionsCount(rs.getInt("subscriptions_count"));
        user.setSubscribersCount(rs.getInt("subscribers_count"));
        user.setEnabled(rs.getBoolean("enabled"));
        publication.setUser(user);
        publication.setImageLink(rs.getString("imagelink"));
        publication.setDescription(rs.getString("description"));
        publication.setPublicationDateTime(rs.getTimestamp("date").toLocalDateTime());
        return publication;
    }
}