package teaspoon.fooding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teaspoon.fooding.domain.review.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
