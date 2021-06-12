package teaspoon.fooding.domain.review;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teaspoon.fooding.domain.BaseEntity;
import teaspoon.fooding.domain.image.ReviewImage;
import teaspoon.fooding.domain.shop.Shop;
import teaspoon.fooding.domain.tag.ReviewTag;
import teaspoon.fooding.domain.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Review extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private double score;

    @OneToMany(mappedBy = "review")
    private List<ReviewImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "review")
    private List<ReviewTag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "review")
    private List<UserLikeReview> likers = new ArrayList<>();

    @Builder
    public Review(Shop shop, User author, String content, double score, List<ReviewImage> images,
                  List<ReviewTag> tags) {
        this.shop = shop;
        this.author = author;
        this.content = content;
        this.score = score;
        this.images = images;
        this.tags = tags;
    }
}
