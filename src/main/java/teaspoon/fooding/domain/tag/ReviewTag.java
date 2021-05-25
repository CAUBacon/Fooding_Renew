package teaspoon.fooding.domain.tag;

import lombok.Getter;
import lombok.NoArgsConstructor;
import teaspoon.fooding.domain.BaseEntity;
import teaspoon.fooding.domain.review.Review;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ReviewTag extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "review_tag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;
}
