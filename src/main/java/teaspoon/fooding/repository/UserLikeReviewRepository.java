package teaspoon.fooding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teaspoon.fooding.domain.review.Review;
import teaspoon.fooding.domain.review.UserLikeReview;
import teaspoon.fooding.domain.user.User;

import java.util.List;

public interface UserLikeReviewRepository extends JpaRepository<UserLikeReview, Long> {

    List<UserLikeReview> findByUserAndReview(User user, Review review);

    long countByReview(Review review);
}
