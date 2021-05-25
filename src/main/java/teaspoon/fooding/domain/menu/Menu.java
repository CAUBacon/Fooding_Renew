package teaspoon.fooding.domain.menu;

import lombok.Getter;
import lombok.NoArgsConstructor;
import teaspoon.fooding.domain.BaseEntity;
import teaspoon.fooding.domain.image.MenuImage;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Menu extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "menu_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_categiry_id")
    private MenuCategory menuCategory;

    private int price;

    private String title;

    private String subTitle;

    @OneToMany(mappedBy = "menu")
    private List<MenuImage> images;
}
