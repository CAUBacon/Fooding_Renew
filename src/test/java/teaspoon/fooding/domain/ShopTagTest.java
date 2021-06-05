package teaspoon.fooding.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import teaspoon.fooding.domain.school.Position;
import teaspoon.fooding.domain.school.School;
import teaspoon.fooding.domain.shop.Address;
import teaspoon.fooding.domain.shop.Restaurant;
import teaspoon.fooding.domain.shop.Shop;
import teaspoon.fooding.domain.tag.ShopTag;
import teaspoon.fooding.domain.tag.Tag;

public class ShopTagTest {

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
    Tag tag = Tag.builder().name("맛집").build();

    @Test
    void increaseCount_count의_기본값은_1이다() {
        // given


        // when
        ShopTag shopTag = ShopTag.builder().shop(shop).tag(tag).build();
        // then
        Assertions.assertThat(shopTag.getCount()).isEqualTo(1);
    }

    @Test
    void increaseCount_count를_1_증가시킨다() {
        // given
        ShopTag shopTag = ShopTag.builder().shop(shop).tag(tag).build();

        // when
        shopTag.increaseCount();

        // then
        Assertions.assertThat(shopTag.getCount()).isEqualTo(2);
    }
}
