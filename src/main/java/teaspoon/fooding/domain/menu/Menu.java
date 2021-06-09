package teaspoon.fooding.domain.menu;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teaspoon.fooding.domain.BaseEntity;
import teaspoon.fooding.domain.image.MenuImage;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Menu extends BaseEntity {

    @OneToMany(mappedBy = "menu")
    private final List<MenuImage> images = new ArrayList<>();
    @Id
    @GeneratedValue
    @Column(name = "menu_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_category_id", nullable = false)
    private MenuCategory menuCategory;
    private int price;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String subTitle;

    public Menu(int price, String title, String subTitle) {
        this.price = price;
        this.title = title;
        this.subTitle = subTitle;
    }

    @Builder
    public Menu(MenuCategory menuCategory, int price, String title, String subTitle) {
        this.menuCategory = menuCategory;
        if (menuCategory != null) {
            menuCategory.getMenus().add(this);
        }
        this.price = price;
        this.title = title;
        this.subTitle = subTitle;
    }

    public void setMenuCategory(MenuCategory menuCategory) {
        if (this.menuCategory != null) {
            this.menuCategory.getMenus().remove(this);
        }
        this.menuCategory = menuCategory;
        if (menuCategory != null) {
            menuCategory.getMenus().add(this);
        }
    }

}
