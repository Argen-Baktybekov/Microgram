package kg.attractor.microgram.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDto {
    private String name;
    private String nickName;
    private String email;
}
