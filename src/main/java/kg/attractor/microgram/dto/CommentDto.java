package kg.attractor.microgram.dto;


import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
//@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class CommentDto {
    private long id;
    private UserDto user;
    private PublicationDto publication;
    private String text;
    private Timestamp dateTime;
}
