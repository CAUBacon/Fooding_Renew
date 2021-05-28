package teaspoon.fooding.domain.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("local")
@Entity
public class LocalUser extends User{
    private String email;
    private String password;

    @Builder
    public LocalUser(String nickname, Gender gender, String email, String password) {
        super(nickname, gender);
        this.email = email;
        this.password = password;
    }
}
