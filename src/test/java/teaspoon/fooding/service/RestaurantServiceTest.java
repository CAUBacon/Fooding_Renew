package teaspoon.fooding.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import teaspoon.fooding.api.advice.exception.CRestaurantNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@SpringBootTest
class RestaurantServiceTest {

    @Autowired
    RestaurantService restaurantService;

    @Test
    void inquireRestaurantDetails_가게가_존재하지_않으면_CRestaurantNotFoundException를_던진다() {
        // given
        // when
        Throwable throwable = catchThrowable(() -> restaurantService.inquireRestaurantDetails(13123L));
        // then
        assertThat(throwable).isInstanceOf(CRestaurantNotFoundException.class);
    }

    @Test
    void inquireRestaurantDetails_가게_정보를_조회해서_RestaurantDetailDto를_반환한다() {
        // given

        // when
        // then
    }
}
