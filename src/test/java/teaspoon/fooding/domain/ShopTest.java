package teaspoon.fooding.domain;

import org.junit.jupiter.api.Test;
import teaspoon.fooding.domain.school.Position;
import teaspoon.fooding.domain.school.School;
import teaspoon.fooding.domain.shop.Address;
import teaspoon.fooding.domain.shop.Restaurant;
import teaspoon.fooding.domain.shop.Shop;
import teaspoon.fooding.domain.tag.ShopTag;
import teaspoon.fooding.domain.tag.Tag;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ShopTest {

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
    void addTag_새로운_태그라면_ShopTag를_새로_만들고_count를_1로_설정한다() {
        // given
        // when
        shop.addTag(tag);
        List<ShopTag> tags = shop.getTags();
        // then
        assertThat(tags.size()).isEqualTo(1);
        ShopTag newShopTag = tags.get(0);
        assertThat(newShopTag.getShop()).isEqualTo(shop);
        assertThat(newShopTag.getTag()).isEqualTo(tag);
        assertThat(newShopTag.getCount()).isEqualTo(1);
    }

    @Test
    void addTag_기존에_존재하던_태그라면_ShopTag를_새로_만들지_않고_count를_1_늘린다() {
        // given
        shop.addTag(tag);

        // when
        shop.addTag(tag);
        List<ShopTag> tags = shop.getTags();
        // then
        assertThat(tags.size()).isEqualTo(1);
        ShopTag newShopTag = tags.get(0);
        assertThat(newShopTag.getShop()).isEqualTo(shop);
        assertThat(newShopTag.getTag()).isEqualTo(tag);
        assertThat(newShopTag.getCount()).isEqualTo(2);
    }

    @Test
    void addTag_태그가_이미_있지만_추가하는_태그가_기존_태그와_다르다면_ShopTag를_새로_만들고_count를_1로_설정한다() {
        // given
        Tag anotherTag = Tag.builder().name("가성비").build();
        shop.addTag(tag);

        // when
        shop.addTag(anotherTag);
        List<ShopTag> tags = shop.getTags();
        // then
        assertThat(tags.size()).isEqualTo(2);
        assertThat(shop.getTags()).extracting("count").containsExactly(1L, 1L);
        assertThat(shop.getTags()).extracting("tag").containsExactly(tag, anotherTag);
    }
}
