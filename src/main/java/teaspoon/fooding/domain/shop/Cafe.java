package teaspoon.fooding.domain.shop;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@NoArgsConstructor
@DiscriminatorValue("cafe")
@Entity
public class Cafe extends Shop {
}
