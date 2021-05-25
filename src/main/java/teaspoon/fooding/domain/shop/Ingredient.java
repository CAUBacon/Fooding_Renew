package teaspoon.fooding.domain.shop;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Ingredient {

    @Id
    @GeneratedValue
    @Column(name = "ingredient_id")
    private Long id;

    private String name;
}
