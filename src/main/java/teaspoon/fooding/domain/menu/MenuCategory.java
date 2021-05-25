package teaspoon.fooding.domain.menu;

import lombok.Getter;
import lombok.NoArgsConstructor;
import teaspoon.fooding.domain.BaseEntity;
import teaspoon.fooding.domain.shop.Shop;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class MenuCategory extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "menu_category_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @OneToMany(mappedBy = "menuCategory")
    private List<Menu> menus;
}
