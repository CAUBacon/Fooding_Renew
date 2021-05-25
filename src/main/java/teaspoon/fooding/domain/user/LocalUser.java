package teaspoon.fooding.domain.user;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@DiscriminatorValue("local")
@Entity
public class LocalUser extends User{
    private String email;
    private String password;
}
