package teaspoon.fooding.domain.image;

import lombok.Getter;
import teaspoon.fooding.domain.shop.Shop;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Entity
public class MenuBoardImage extends Image{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;
}
