package teaspoon.fooding.domain.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("kakao")
@Entity
public class KakaoUser extends User{
    private String snsId;
    private final String password = null;
}
