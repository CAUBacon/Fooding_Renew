package teaspoon.fooding.domain.shop;

import lombok.Getter;
import lombok.NoArgsConstructor;
import teaspoon.fooding.domain.BaseEntity;
import teaspoon.fooding.domain.category.ShopCategory;
import teaspoon.fooding.domain.image.ShopImage;
import teaspoon.fooding.domain.menu.MenuCategory;
import teaspoon.fooding.domain.school.School;
import teaspoon.fooding.domain.tag.ShopTag;
import teaspoon.fooding.domain.tag.Tag;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Getter
@NoArgsConstructor
@DiscriminatorColumn(name = "type")
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class Shop extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "shop_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @Column(nullable = false)
    private String contact;

    @Column(nullable = false)
    private String operationTime;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "shop")
    private List<ShopImage> images = new ArrayList<>();

//    @OneToMany(mappedBy = "shop")
//    private List<MenuBoardImage> menuBoardImages;

    @OneToMany(mappedBy = "shop")
    private List<MenuCategory> menuCategories = new ArrayList<>();

//    @OneToMany(mappedBy = "shop")
//    private List<Review> reviews;
//
//    @OneToMany(mappedBy = "shop")
//    private List<ShopLike> likeUsers;
//
    @OneToMany(mappedBy = "shop")
    private List<ShopTag> tags= new ArrayList<>();

    @OneToMany(mappedBy = "shop")
    private List<ShopCategory> categories  = new ArrayList<>();

    public Shop(String name, School school, String contact, String operationTime, Address address) {
        this.name = name;
        this.school = school;
        this.contact = contact;
        this.operationTime = operationTime;
        this.address = address;
    }

    public void addTag(Tag tag) {
        Optional<ShopTag> sameTag = tags.stream().filter(st -> st.getTag().equals(tag)).findFirst();
        sameTag.ifPresentOrElse(ShopTag::increaseCount, () -> {
            this.tags.add(
                    ShopTag.builder()
                            .shop(this)
                            .tag(tag)
                            .build()
            );
        });
    }
}
