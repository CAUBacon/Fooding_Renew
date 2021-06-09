package teaspoon.fooding.domain.category;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teaspoon.fooding.domain.BaseEntity;
import teaspoon.fooding.domain.shop.Shop;

import javax.persistence.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ShopCategory extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "shop_category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Builder
    public ShopCategory(Shop shop, Category category) {
        this.shop = shop;
        this.category = category;
    }
}
