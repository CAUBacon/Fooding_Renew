package teaspoon.fooding.domain.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import teaspoon.fooding.domain.BaseEntity;
import teaspoon.fooding.domain.shop.Shop;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class ShopCategory extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "shop_category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
