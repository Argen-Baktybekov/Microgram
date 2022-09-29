package kg.attractor.microgram.dto;

import kg.attractor.microgram.entity.Publication;
import kg.attractor.microgram.entity.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
@RequiredArgsConstructor
@Data
public class CommentDto {
    private int id;
    private User user;
    private Publication publication;
    private String text;
    private LocalDateTime CommentDateTime;
}
