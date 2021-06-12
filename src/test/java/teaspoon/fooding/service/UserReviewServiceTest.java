package teaspoon.fooding.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import teaspoon.fooding.domain.review.Review;
import teaspoon.fooding.domain.school.Position;
import teaspoon.fooding.domain.school.School;
import teaspoon.fooding.domain.shop.Address;
import teaspoon.fooding.domain.shop.Restaurant;
import teaspoon.fooding.domain.shop.Shop;
import teaspoon.fooding.domain.user.Gender;
import teaspoon.fooding.domain.user.LocalUser;
import teaspoon.fooding.domain.user.User;
import teaspoon.fooding.repository.*;

import java.util.Collections;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@Transactional
@SpringBootTest
class UserReviewServiceTest {

    @Autowired
    UserReviewService userReviewService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserLikeReviewRepository userLikeReviewRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    PositionRepository positionRepository;
    @Autowired
    SchoolRepository schoolRepository;

    School school = School.builder().name("중앙대학교").build();
    Position position = Position.builder().school(school).name("정문").build();
    Shop shop =
            Restaurant.builder()
                    .name("이름")
                    .address(new Address("주소", position))
                    .contact("02-1234-1234")
                    .operationTime("연중무휴")
                    .school(school)
                    .build();

    User user1 = LocalUser.builder()
            .nickname("홍길동")
            .gender(Gender.MALE)
            .password("1234")
            .email("test@naver.com")
            .build();

    User user2 = LocalUser.builder()
            .nickname("김갑수")
            .gender(Gender.FEMALE)
            .password("asdf")
            .email("asdf@naver.com")
            .build();

    Review review = Review.builder()
            .content("괜찮은 식당")
            .score(4.5)
            .images(Collections.emptyList())
            .tags(Collections.emptyList())
            .author(user1)
            .shop(shop)
            .build();

    @BeforeEach
    public void init() {
        schoolRepository.saveAll(asList(school));
        positionRepository.saveAll(asList(position));
        shopRepository.saveAll(asList(shop));
        userRepository.saveAll(asList(user1, user2));
        reviewRepository.saveAll(asList(review));
    }

    @Test
    void likeReview_리뷰를_좋아요_한다() {
        // given

        // when
        long result = userReviewService.likeReview(user2, review);
        // then
        assertThat(review.getLikers().size()).isEqualTo(1);
        assertThat(review.getLikers()).extracting("user", "review").contains(tuple(user2, review));

        assertThat(result).isEqualTo(1);
    }

    @Test
    void likeReview_이미_좋아요를_누른_리뷰를_또_좋아요를_누르면_에러를_던지지_않고_대신_중복_좋아요도_되지_않는다() {
        // given
        userReviewService.likeReview(user2, review);
        // when
        long result = userReviewService.likeReview(user2, review);
        // then
        assertThat(review.getLikers().size()).isEqualTo(1);
        assertThat(review.getLikers()).extracting("user", "review").contains(tuple(user2, review));

        assertThat(result).isEqualTo(1);
    }
}
