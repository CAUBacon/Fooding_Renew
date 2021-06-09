package teaspoon.fooding.domain.menu;

import org.junit.jupiter.api.Test;
import teaspoon.fooding.domain.school.Position;
import teaspoon.fooding.domain.school.School;
import teaspoon.fooding.domain.shop.Address;
import teaspoon.fooding.domain.shop.Restaurant;
import teaspoon.fooding.domain.shop.Shop;

import static org.assertj.core.api.Assertions.assertThat;

class MenuTest {

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

    Menu menu1 = Menu.builder().title("A").subTitle("sA").price(1000).build();
    Menu menu2 = Menu.builder().title("B").subTitle("sB").price(3000).build();
    Menu menu3 = Menu.builder().title("C").subTitle("sC").price(2000).build();
    MenuCategory menuCategory1 = MenuCategory.builder().name("가").shop(shop).build();
    MenuCategory menuCategory2 = MenuCategory.builder().name("나").shop(shop).build();

    @Test
    void constructor_menuCategory를_전달하면_정상적으로_설정된다() {
        // given
        // when
        Menu menu = Menu.builder()
                .title("국밥")
                .subTitle("뜨끈한 밥")
                .price(3000)
                .menuCategory(menuCategory1)
                .build();

        // then
        assertThat(menu.getMenuCategory()).isEqualTo(menuCategory1);
        assertThat(menuCategory1.getMenus()).contains(menu);
    }

    @Test
    void constructor_menuCategory를_전달하지_않으면_MenuCategory가_없는_메뉴가_만들어진다() {
        // given
        // when
        Menu menu = Menu.builder()
                .title("국밥")
                .subTitle("뜨끈한 밥")
                .price(3000)
                .build();

        // then
        assertThat(menu.getMenuCategory()).isNull();
        assertThat(menuCategory1.getMenus()).doesNotContain(menu);
    }

    @Test
    void setMenuCategory_카테고리가_없던_메뉴에_메뉴_카테고리를_설정한다() {
        // given

        // when
        menu1.setMenuCategory(menuCategory1);

        // then
        assertThat(menu1.getMenuCategory()).isEqualTo(menuCategory1);

        assertThat(menuCategory1.getMenus().size()).isEqualTo(1);
        assertThat(menuCategory1.getMenus()).contains(menu1);
    }

    @Test
    void setMenuCategory_카테고리가_없던_2개의_메뉴에_메뉴_카테고리를_설정한다() {
        // given

        // when
        menu1.setMenuCategory(menuCategory1);
        menu2.setMenuCategory(menuCategory1);

        // then
        assertThat(menu1.getMenuCategory()).isEqualTo(menuCategory1);
        assertThat(menu2.getMenuCategory()).isEqualTo(menuCategory1);

        assertThat(menuCategory1.getMenus().size()).isEqualTo(2);
        assertThat(menuCategory1.getMenus()).contains(menu1, menu2);
    }

    @Test
    void setMenuCategory_카테고리가_있던_메뉴에_메뉴_카테고리를_설정한다() {
        // given
        menu1.setMenuCategory(menuCategory2);
        menu3.setMenuCategory(menuCategory2);

        // when
        menu1.setMenuCategory(menuCategory1);
        menu2.setMenuCategory(menuCategory1);

        // then
        assertThat(menu1.getMenuCategory()).isEqualTo(menuCategory1);
        assertThat(menu2.getMenuCategory()).isEqualTo(menuCategory1);
        assertThat(menu3.getMenuCategory()).isEqualTo(menuCategory2);

        assertThat(menuCategory1.getMenus().size()).isEqualTo(2);
        assertThat(menuCategory2.getMenus().size()).isEqualTo(1);

        assertThat(menuCategory1.getMenus()).contains(menu1, menu2);
        assertThat(menuCategory2.getMenus()).contains(menu3);
    }
}
