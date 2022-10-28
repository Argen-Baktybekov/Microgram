package kg.attractor.microgram.entity;

import lombok.*;

import java.time.LocalDateTime;
//@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Publication {
    private int id;
    private User user;
    private String imageLink;
    private String description;
    private LocalDateTime PublicationDateTime;

//    public Publication(int id, User user, String imageLink, String description, LocalDateTime publicationDateTime) {
//        this.id = id;
//        this.user = user;
//        this.imageLink = imageLink;
//        this.description = description;
//        PublicationDateTime = publicationDateTime;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getPublicationDateTime() {
        return PublicationDateTime;
    }

    public void setPublicationDateTime(LocalDateTime publicationDateTime) {
        PublicationDateTime = publicationDateTime;
    }
}