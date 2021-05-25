package teaspoon.fooding.domain.shop;

import lombok.Getter;
import lombok.NoArgsConstructor;
import teaspoon.fooding.domain.BaseEntity;
import teaspoon.fooding.domain.category.ShopCategory;
import teaspoon.fooding.domain.menu.MenuCategory;
import teaspoon.fooding.domain.tag.ShopTag;
import teaspoon.fooding.domain.image.MenuBoardImage;
import teaspoon.fooding.domain.image.ShopImage;
import teaspoon.fooding.domain.review.Review;
import teaspoon.fooding.domain.school.School;

import javax.persistence.*;
import java.util.List;


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

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    private String contact;

    private String operationTime;

    @Embedded
    private Address address;

//    @OneToMany(mappedBy = "shop")
//    private List<ShopImage> images;
//
//    @OneToMany(mappedBy = "shop")
//    private List<MenuBoardImage> menuBoardImages;
//
//    @OneToMany(mappedBy = "shop")
//    private List<MenuCategory> menuCategories;
//
//    @OneToMany(mappedBy = "shop")
//    private List<Review> reviews;
//
//    @OneToMany(mappedBy = "shop")
//    private List<ShopLike> likeUsers;
//
//    @OneToMany(mappedBy = "shop")
//    private List<ShopTag> tags;
//
//    @OneToMany(mappedBy = "shop")
//    private List<ShopCategory> categories;


}
