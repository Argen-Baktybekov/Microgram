package kg.attractor.microgram.entity;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
//@Builder
@Data
public class User {
    private int id;
    private String name;
    private String nickName;
    private String email;
    private String password;
    private int publicCount;
    private int subscriptionsCount;
    private int subscribersCount;
    private boolean enabled;

    public User( String name, String email, String password) {
        this.id = 0;
        this.name = name;
        this.email = email;
        this.password = password;
        this.publicCount = 0;
        this.subscriptionsCount =0;
        this.subscribersCount = 0;
        this.enabled = true;
    }
}
