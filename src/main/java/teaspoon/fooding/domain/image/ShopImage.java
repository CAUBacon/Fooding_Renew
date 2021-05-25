package teaspoon.fooding.domain.image;

import lombok.Getter;
import teaspoon.fooding.domain.shop.Shop;

import javax.persistence.*;

@Getter
@Entity
public class ShopImage extends Image{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="shop_id")
    private Shop shop;
}
