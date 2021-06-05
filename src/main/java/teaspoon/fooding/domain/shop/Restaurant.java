package teaspoon.fooding.domain.shop;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@NoArgsConstructor
@DiscriminatorValue("restaurant")
@Entity
public class Restaurant extends Shop {

    @OneToMany(mappedBy = "restaurant")
    private List<RestaurantIngredient> ingredients;
}
