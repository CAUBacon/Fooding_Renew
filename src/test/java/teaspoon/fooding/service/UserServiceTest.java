package teaspoon.fooding.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import teaspoon.fooding.api.advice.exception.CShopLikeDuplicatedException;
import teaspoon.fooding.domain.school.Position;
import teaspoon.fooding.domain.school.School;
import teaspoon.fooding.domain.shop.Address;
import teaspoon.fooding.domain.shop.Restaurant;
import teaspoon.fooding.domain.shop.ShopLike;
import teaspoon.fooding.domain.user.Gender;
import teaspoon.fooding.domain.user.LocalUser;
import teaspoon.fooding.repository.*;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    SchoolRepository schoolRepository;
    @Autowired
    PositionRepository positionRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    LocalUserRepository userRepository;
    @Autowired
    ShopLikeRepository shopLikeRepository;

    LocalUser user;
    School school;
    Position position;
    Restaurant shop;

    @BeforeEach
    void beforeEach() {
        user = new LocalUser("홍길동", Gender.MALE, "abc@test.com", "1234");
        school = School.builder()
                .name("중앙대")
                .build();
        position = Position.builder()
                .name("정문")
                .school(school)
                .build();
        shop = Restaurant.builder()
                .name("칠기")
                .address(new Address("서울시 중앙대", position))
                .school(school)
                .contact("010-1234-1234")
                .operationTime("연중 무휴")
                .build();
        schoolRepository.save(school);
        positionRepository.save(position);
        restaurantRepository.save(shop);
        userRepository.save(user);
    }

    @Test
    void likeShop_가게를_좋아한다() {
        // given

        // when
        userService.likeShop(user, shop);
        // then
        List<ShopLike> shopLikes = shopLikeRepository.findByUser(user);
        assertThat(shopLikes.size()).isEqualTo(1);
        assertThat(shopLikes).extracting("user", "shop").contains(tuple(user, shop));
    }

    @Test
    void likeShop_이미_좋아요를_누른_가게는_또_좋아할_수_없다() {
        // given
        userService.likeShop(user, shop);
        // when
        Throwable throwable = catchThrowable(() -> userService.likeShop(user, shop));
        // then
        assertThat(throwable).isInstanceOf(CShopLikeDuplicatedException.class);
    }

    @Test
    void unlikeShop_가게를_좋아요를_취소한다() {
        // given
        userService.likeShop(user, shop);

        // when
        userService.unlikeShop(user, shop);
        // then
        List<ShopLike> shopLikes = shopLikeRepository.findByUser(user);
        assertThat(shopLikes.size()).isEqualTo(0);
    }

    @Test
    void unlikeShop_좋아요를_누르지_않은_가게를_좋아요_취소하더라도_에러를_던지지_않고_무시한다() {
        // given
        // when
        userService.unlikeShop(user, shop);
        // then
        List<ShopLike> shopLikes = shopLikeRepository.findByUser(user);
        assertThat(shopLikes.size()).isEqualTo(0);
    }

}
