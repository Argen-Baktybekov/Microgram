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
    private User user;
    private Publication publication;
    private LocalDateTime time;
}
