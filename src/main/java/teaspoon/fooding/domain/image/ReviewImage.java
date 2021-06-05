package teaspoon.fooding.domain.image;

import lombok.Getter;
import lombok.NoArgsConstructor;
import teaspoon.fooding.domain.review.Review;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter
@Entity
public class ReviewImage extends Image{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;
}
