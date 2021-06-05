package teaspoon.fooding.domain.shop;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teaspoon.fooding.domain.school.School;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@DiscriminatorValue("restaurant")
@Entity
public class Restaurant extends Shop {

    @OneToMany(mappedBy = "restaurant")
    private List<RestaurantIngredient> ingredients = new ArrayList<>();;

    @Builder
    public Restaurant(String name, School school, String contact, String operationTime, Address address) {
        super(name, school, contact, operationTime, address);
    }
}
