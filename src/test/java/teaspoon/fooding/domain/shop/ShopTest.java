package teaspoon.fooding.domain.shop;

import org.junit.jupiter.api.Test;
import teaspoon.fooding.domain.category.Category;
import teaspoon.fooding.domain.menu.Menu;
import teaspoon.fooding.domain.menu.MenuCategory;
import teaspoon.fooding.domain.school.Position;
import teaspoon.fooding.domain.school.School;
import teaspoon.fooding.domain.tag.ShopTag;
import teaspoon.fooding.domain.tag.Tag;
import teaspoon.fooding.domain.user.Gender;
import teaspoon.fooding.domain.user.LocalUser;

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

    Menu menu1 = Menu.builder().title("A").subTitle("sA").price(1000).build();
    Menu menu2 = Menu.builder().title("B").subTitle("sB").price(3000).build();
    Menu menu3 = Menu.builder().title("C").subTitle("sC").price(2000).build();
    MenuCategory menuCategory1 = MenuCategory.builder().name("가").shop(shop).build();
    MenuCategory menuCategory2 = MenuCategory.builder().name("나").shop(shop).build();

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

    @Test
    void addTag_한번에_여러_태그를_추가할_수_있다() {
        Tag anotherTag = Tag.builder().name("가성비").build();

        // when
        shop.addTag(tag, tag, anotherTag);
        List<ShopTag> tags = shop.getTags();
        // then
        assertThat(tags.size()).isEqualTo(2);
        assertThat(shop.getTags()).extracting("count").containsExactly(2L, 1L);
        assertThat(shop.getTags()).extracting("tag").containsExactly(tag, anotherTag);
    }

    @Test
    void addMenu_메뉴카테고리를_가지고있지_않던_메뉴를_추가한다() {
        // given
        // when
        shop.addMenu(menuCategory1, menu1);

        // then
        assertThat(menuCategory1.getMenus().size()).isEqualTo(1);
        assertThat(menuCategory1.getMenus()).contains(menu1);

        assertThat(menu1.getMenuCategory()).isEqualTo(menuCategory1);
    }

    @Test
    void addMenu_메뉴카테고리를_가지고_있던_메뉴를_추가한다() {
        // given
        menu1.setMenuCategory(menuCategory2);
        menu2.setMenuCategory(menuCategory2);
        // when
        shop.addMenu(menuCategory1, menu1);
        shop.addMenu(menuCategory2, menu3);

        // then
        assertThat(menuCategory1.getMenus().size()).isEqualTo(1);
        assertThat(menuCategory1.getMenus()).contains(menu1);
        assertThat(menuCategory2.getMenus().size()).isEqualTo(2);
        assertThat(menuCategory2.getMenus()).contains(menu2, menu3);

        assertThat(menu1.getMenuCategory()).isEqualTo(menuCategory1);
        assertThat(menu2.getMenuCategory()).isEqualTo(menuCategory2);
        assertThat(menu3.getMenuCategory()).isEqualTo(menuCategory2);
    }

    @Test
    void addCategories_ShopCategory를_만들어_shop에_추가한다() {
        // given
        Category category1 = Category.builder()
                .name("한식")
                .build();
        Category category2 = Category.builder()
                .name("양식")
                .build();
        // when
        shop.addCategories(category1, category2);

        // then
        assertThat(shop.getCategories().size()).isEqualTo(2);
        assertThat(shop.getCategories()).extracting("category").contains(category1, category2);
    }

    @Test
    void addShopImage_ShopImage를_만든다() {
        // given
        LocalUser uploader = LocalUser.builder()
                .nickname("홍길동")
                .password("1234")
                .email("test@naver.com")
                .gender(Gender.FEMALE)
                .build();
        String imageLink = "image.com";
        // when
        shop.addShopImage(uploader, imageLink);
        // then
        assertThat(shop.getImages().size()).isEqualTo(1);
        assertThat(shop.getImages()).extracting("imageLink").contains(imageLink);
        assertThat(shop.getImages()).extracting("uploader").contains(uploader);
    }
}
