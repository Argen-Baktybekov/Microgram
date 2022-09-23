package kg.attractor.microgram.entity;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Subscription {
    private User subscriptions;
    private User subscribers;
    private LocalDateTime time;

    public Subscription(User subscriptions, User subscribers, LocalDateTime time) {
        this.subscriptions = subscriptions;
        this.subscribers = subscribers;
        this.time = time;
    }

    public User getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(User subscriptions) {
        this.subscriptions = subscriptions;
    }

    public User getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(User subscribers) {
        this.subscribers = subscribers;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
