package teaspoon.fooding.domain.review;

import lombok.Getter;
import teaspoon.fooding.domain.BaseEntity;
import teaspoon.fooding.domain.tag.ReviewTag;
import teaspoon.fooding.domain.image.ReviewImage;
import teaspoon.fooding.domain.shop.Shop;
import teaspoon.fooding.domain.user.User;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
public class Review extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    private String content;

    private Double score;

    @OneToMany(mappedBy = "review")
    private List<ReviewImage> images;

    @OneToMany(mappedBy = "review")
    private List<ReviewTag> tags;

}
