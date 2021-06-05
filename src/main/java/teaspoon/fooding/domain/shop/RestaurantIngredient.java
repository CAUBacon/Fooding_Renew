package teaspoon.fooding.domain.shop;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class RestaurantIngredient {
    @Id @GeneratedValue
    @Column(name = "restaurant_ingredient_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

}
