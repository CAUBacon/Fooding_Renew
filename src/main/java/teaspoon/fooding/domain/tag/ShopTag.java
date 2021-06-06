package teaspoon.fooding.domain.tag;

import lombok.Builder;
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
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;

    @Column(nullable = false)
    private Long count;

    @Builder
    public ShopTag(Shop shop, Tag tag) {
        this.shop = shop;
        this.tag = tag;
        this.count = 1L;
    }

    public void increaseCount() {
        this.count += 1;
    }
}
