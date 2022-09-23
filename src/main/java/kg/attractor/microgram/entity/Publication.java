package kg.attractor.microgram.entity;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Publication {
    private int id;
    private String imageLink;
    private String description;
    private LocalDateTime PublicationDateTime;

    public Publication(int id, String imageLink, String description, LocalDateTime publicationDateTime) {
        this.id = id;
        this.imageLink = imageLink;
        this.description = description;
        PublicationDateTime = publicationDateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
