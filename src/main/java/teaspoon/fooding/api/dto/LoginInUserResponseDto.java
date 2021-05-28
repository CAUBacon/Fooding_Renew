package teaspoon.fooding.api.dto;

import lombok.Builder;
import lombok.Getter;
import teaspoon.fooding.domain.user.Gender;


@Getter
public class LoginInUserResponseDto {
    private final Long id;
    private final String nickname;
    private final Gender gender;
    private final String token;

    @Builder
    public LoginInUserResponseDto(Long id, String nickname, Gender gender, String token) {
        this.id = id;
        this.nickname = nickname;
        this.gender = gender;
        this.token = token;
    }
}
