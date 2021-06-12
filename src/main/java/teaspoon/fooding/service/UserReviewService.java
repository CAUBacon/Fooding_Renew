package teaspoon.fooding.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teaspoon.fooding.domain.review.Review;
import teaspoon.fooding.domain.review.UserLikeReview;
import teaspoon.fooding.domain.user.User;
import teaspoon.fooding.repository.UserLikeReviewRepository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserReviewService {

    private final UserLikeReviewRepository userLikeReviewRepository;

    public long likeReview(User user, Review review) {
        List<UserLikeReview> likeHistoryForReview = userLikeReviewRepository.findByUserAndReview(user, review);
        if (likeHistoryForReview.size() == 0) {
            UserLikeReview userLikeReview = UserLikeReview.builder()
                    .user(user)
                    .review(review)
                    .build();
            userLikeReviewRepository.save(userLikeReview);
        }
        return userLikeReviewRepository.countByReview(review);
    }
}
