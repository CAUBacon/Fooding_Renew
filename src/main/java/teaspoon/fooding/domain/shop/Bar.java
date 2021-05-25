package teaspoon.fooding.domain.shop;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@NoArgsConstructor
@DiscriminatorValue("bar")
@Entity
public class Bar extends Shop{
}
