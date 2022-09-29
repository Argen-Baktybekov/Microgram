package kg.attractor.microgram.dto;

import kg.attractor.microgram.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PublicationDto {
    private int id;
    private User user;
    private String imageLink;
    private String description;
    private LocalDateTime PublicationDateTime;
}
