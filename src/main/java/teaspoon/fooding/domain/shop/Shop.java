package teaspoon.fooding.domain.shop;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teaspoon.fooding.domain.BaseEntity;
import teaspoon.fooding.domain.category.Category;
import teaspoon.fooding.domain.category.ShopCategory;
import teaspoon.fooding.domain.image.ShopImage;
import teaspoon.fooding.domain.menu.Menu;
import teaspoon.fooding.domain.menu.MenuCategory;
import teaspoon.fooding.domain.school.School;
import teaspoon.fooding.domain.tag.ShopTag;
import teaspoon.fooding.domain.tag.Tag;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorColumn(name = "type")
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class Shop extends BaseEntity {

    @OneToMany(mappedBy = "shop")
    private final List<ShopImage> images = new ArrayList<>();
    @OneToMany(mappedBy = "shop")
    private final List<MenuCategory> menuCategories = new ArrayList<>();
    //    @OneToMany(mappedBy = "shop")
//    private List<Review> reviews;
//
//    @OneToMany(mappedBy = "shop")
//    private List<ShopLike> likeUsers;
//
    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private final List<ShopTag> tags = new ArrayList<>();
    @OneToMany(mappedBy = "shop")
    private final List<ShopCategory> categories = new ArrayList<>();
    @Id
    @GeneratedValue
    @Column(name = "shop_id")
    private Long id;
    @Column(nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    //    @OneToMany(mappedBy = "shop")
//    private List<MenuBoardImage> menuBoardImages;
    @Column(nullable = false)
    private String contact;
    @Column(nullable = false)
    private String operationTime;
    @Embedded
    private Address address;

    public Shop(String name, School school, String contact, String operationTime, Address address) {
        this.name = name;
        this.school = school;
        this.contact = contact;
        this.operationTime = operationTime;
        this.address = address;
    }

    public void addCategories(Category... categories) {
        Arrays.stream(categories).forEach(category -> {
            ShopCategory newShopCategory = ShopCategory.builder()
                    .category(category)
                    .shop(this)
                    .build();
            this.categories.add(newShopCategory);
        });
    }

    public void addTag(Tag... newTags) {
        Arrays.stream(newTags).forEach(newTag -> {
            Optional<ShopTag> sameTag = this.tags.stream().filter(st -> st.getTag().equals(newTag)).findFirst();
            sameTag.ifPresentOrElse(ShopTag::increaseCount, () -> {
                this.tags.add(
                        ShopTag.builder()
                                .shop(this)
                                .tag(newTag)
                                .build()
                );
            });
        });
    }

    public void addMenu(MenuCategory menuCategory, Menu... newMenus) {
        if (menuCategory.getShop() != this) {
            throw new IllegalStateException();
        }
        Arrays.stream(newMenus).forEach(newMenu -> {
            newMenu.setMenuCategory(menuCategory);
        });
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", school=" + school +
                ", contact='" + contact + '\'' +
                ", operationTime='" + operationTime + '\'' +
                ", address=" + address +
                ", images=" + images +
                ", menuCategories=" + menuCategories +
                ", tags=" + tags +
                ", categories=" + categories +
                '}';
    }
}
