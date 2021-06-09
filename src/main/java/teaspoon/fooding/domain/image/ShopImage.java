package teaspoon.fooding.domain.image;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teaspoon.fooding.domain.shop.Shop;
import teaspoon.fooding.domain.user.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("shop")
@Entity
public class ShopImage extends Image {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @Builder
    public ShopImage(String imageLink, User uploader, Shop shop) {
        super(imageLink, uploader);
        this.shop = shop;
    }
}
