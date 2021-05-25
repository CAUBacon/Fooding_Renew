package teaspoon.fooding.domain.tag;

import lombok.Getter;
import lombok.NoArgsConstructor;
import teaspoon.fooding.domain.BaseEntity;
import teaspoon.fooding.domain.shop.Shop;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ShopTag extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "shop_tag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    private int count;
}
