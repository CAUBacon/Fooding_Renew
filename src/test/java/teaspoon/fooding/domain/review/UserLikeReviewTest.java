package teaspoon.fooding.domain.review;

import org.junit.jupiter.api.Test;
import teaspoon.fooding.domain.school.Position;
import teaspoon.fooding.domain.school.School;
import teaspoon.fooding.domain.shop.Address;
import teaspoon.fooding.domain.shop.Restaurant;
import teaspoon.fooding.domain.shop.Shop;
import teaspoon.fooding.domain.user.Gender;
import teaspoon.fooding.domain.user.LocalUser;
import teaspoon.fooding.domain.user.User;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class UserLikeReviewTest {

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

    @Test
    void construct_생성하면_review의_likers에_추가된다() {
        // given

        // when
        UserLikeReview userLikeReview = UserLikeReview.builder()
                .user(user2)
                .review(review)
                .build();
        // then
        assertThat(userLikeReview.getReview()).isEqualTo(review);
        assertThat(userLikeReview.getUser()).isEqualTo(user2);

        assertThat(review.getLikers().size()).isEqualTo(1);
        assertThat(review.getLikers()).extracting("user", "review").contains(tuple(user2, review));
    }
}
