package kg.attractor.microgram.dto;

import kg.attractor.microgram.entity.Publication;
import kg.attractor.microgram.entity.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Data
public class LikeDto {
    private int id;
    private UserDto user;
    private PublicationDto publication;
    private LocalDateTime time;
}
