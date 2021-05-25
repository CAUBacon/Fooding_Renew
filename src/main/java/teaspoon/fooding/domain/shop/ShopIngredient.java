package teaspoon.fooding.domain.shop;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class ShopIngredient {
    @Id @GeneratedValue
    @Column(name = "shop_ingredient_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

}
