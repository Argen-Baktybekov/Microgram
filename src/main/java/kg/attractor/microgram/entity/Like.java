package kg.attractor.microgram.entity;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
//@RequiredArgsConstructor
//@Builder
@Data
public class Like {
    private int id;
    private User user;
    private Publication publication;
    private LocalDateTime time;

    public Like(User user, Publication publication, LocalDateTime time) {
        this.user = user;
        this.publication = publication;
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
