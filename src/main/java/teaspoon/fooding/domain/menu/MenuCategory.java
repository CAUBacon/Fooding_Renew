package teaspoon.fooding.domain.menu;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teaspoon.fooding.domain.BaseEntity;
import teaspoon.fooding.domain.shop.Shop;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MenuCategory extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "menu_category_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @OneToMany(mappedBy = "menuCategory", cascade = CascadeType.ALL)
    private final List<Menu> menus = new ArrayList<>();

    @Builder
    public MenuCategory(String name, Shop shop) {
        this.name = name;
        this.shop = shop;
    }
}
