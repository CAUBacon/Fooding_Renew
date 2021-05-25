package teaspoon.fooding.domain.shop;

import lombok.Getter;
import lombok.NoArgsConstructor;
import teaspoon.fooding.domain.BaseEntity;
import teaspoon.fooding.domain.user.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ShopLike extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "shop_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
