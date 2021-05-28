package teaspoon.fooding.domain.user;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@DiscriminatorValue("kakao")
@Entity
public class KakaoUser extends User{
    private String snsId;
    private final String password = null;
}
