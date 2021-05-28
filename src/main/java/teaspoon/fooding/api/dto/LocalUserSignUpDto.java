package teaspoon.fooding.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import teaspoon.fooding.domain.user.Gender;
import teaspoon.fooding.domain.user.LocalUser;

@NoArgsConstructor
@Getter
@Setter
public class LocalUserSignUpDto {
    private String email;
    private String password;
    private String nickname;
    private Gender gender;

    public LocalUserSignUpDto(String email, String password, String nickname, Gender gender) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
    }

    public LocalUser toUser() {
        return LocalUser.builder()
                .nickname(nickname)
                .gender(gender)
                .email(email)
                .password(password)
                .build();
    }
}
