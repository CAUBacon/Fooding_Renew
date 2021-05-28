package teaspoon.fooding.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LocalUserSignInDto {
    private String email;
    private String password;

    public LocalUserSignInDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
