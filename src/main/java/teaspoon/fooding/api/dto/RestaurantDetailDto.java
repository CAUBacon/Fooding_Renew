package teaspoon.fooding.api.dto;

import lombok.Builder;
import lombok.Getter;
import teaspoon.fooding.domain.category.Category;
import teaspoon.fooding.domain.category.ShopCategory;
import teaspoon.fooding.domain.image.ShopImage;
import teaspoon.fooding.domain.menu.MenuCategory;
import teaspoon.fooding.domain.school.School;
import teaspoon.fooding.domain.shop.Address;
import teaspoon.fooding.domain.shop.Ingredient;
import teaspoon.fooding.domain.shop.RestaurantIngredient;
import teaspoon.fooding.domain.tag.ShopTag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Getter
public class RestaurantDetailDto {
    private final Long id;
    private final String name;
    private final School school;
    private final String contact;
    private final String operationTime;
    private final Address address;
    private final List<ShopImage> images;
    private final List<MenuCategory> menuCategories;
    private final Long numberOfLike;
    private final Map<String, Long> tags = new HashMap<>();
    private final List<Category> categories;
    private final List<Ingredient> ingredients;

    @Builder
    public RestaurantDetailDto(Long id, String name, School school, String contact, String operationTime,
                               Address address, List<ShopImage> images, List<MenuCategory> menuCategories,
                               Long numberOfLike, List<ShopTag> tags, List<ShopCategory> categories,
                               List<RestaurantIngredient> ingredients) {
        this.id = id;
        this.name = name;
        this.school = school;
        this.contact = contact;
        this.operationTime = operationTime;
        this.address = address;
        this.images = images;
        this.menuCategories = menuCategories;
        this.numberOfLike = numberOfLike;
        tags.forEach(shopTag -> this.tags.put(shopTag.getTag().getName(), shopTag.getCount()));
        this.categories = categories.stream().map(ShopCategory::getCategory).collect(toList());
        this.ingredients = ingredients.stream().map(RestaurantIngredient::getIngredient).collect(toList());
    }
}
