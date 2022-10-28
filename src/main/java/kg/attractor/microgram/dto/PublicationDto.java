package kg.attractor.microgram.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PublicationDto {
    private int id;
    private UserDto user;
    private String imageLink;
    private String description;
    private LocalDateTime dateTime;
}
