package kg.attractor.microgram.service;

import kg.attractor.microgram.dto.CommentDto;
import kg.attractor.microgram.dto.PublicationDto;
import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.entity.Comment;
import kg.attractor.microgram.entity.Publication;
import kg.attractor.microgram.entity.User;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentRowMapper implements RowMapper<CommentDto> {

    @Override
    public CommentDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        CommentDto comment = new CommentDto();
        comment.setId(rs.getInt("id"));
        UserDto user = new UserDto();
        user.setId(rs.getInt("user_id"));
        user.setEmail(rs.getString("email"));
        user.setName(rs.getString("name"));
        user.setNickName(rs.getString("nick_name"));
        comment.setUser(user);
        PublicationDto publication = new PublicationDto();
        publication.setId(rs.getInt("publication_id"));
        UserDto userp = new UserDto();
        userp.setId(rs.getInt("user_id"));
        userp.setEmail(rs.getString("email"));
        userp.setName(rs.getString("name"));
        userp.setNickName(rs.getString("nick_name"));
        publication.setUser(userp);
        publication.setImageLink(rs.getString("imagelink"));
        publication.setDescription(rs.getString("description"));
        publication.setDateTime(rs.getTimestamp("date").toLocalDateTime());
     comment.setPublication(publication);
     comment.setText(rs.getString("text"));
     comment.setDateTime(rs.getTimestamp("date"));
        return comment;
    }


}
