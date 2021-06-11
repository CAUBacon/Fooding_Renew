package teaspoon.fooding.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import teaspoon.fooding.api.advice.exception.CRestaurantNotFoundException;
import teaspoon.fooding.api.dto.RestaurantDetailDto;
import teaspoon.fooding.domain.shop.Restaurant;
import teaspoon.fooding.repository.RestaurantRepository;
import teaspoon.fooding.repository.ShopRepository;

@Slf4j
@RequiredArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final ShopRepository shopRepository;

    public RestaurantDetailDto inquireRestaurantDetails(Long restaurantId) {
        Restaurant restaurant =
                restaurantRepository.inquireRestaurantDetails(restaurantId).orElseThrow(CRestaurantNotFoundException::new);
        Long numberOfLike = shopRepository.countNumberOfLike(restaurantId);

        return null;

//        return RestaurantDetailDto
//                .builder()
//                .id(restaurantId)
//                .school(restaurant.getSchool())
//                .name(restaurant.getName())
//                .address(restaurant.getAddress())
//                .contact(restaurant.getContact())
//                .operationTime(restaurant.getOperationTime())
//                .numberOfLike(numberOfLike)
//                .ingredients(restaurant.getIngredients())
//                .categories(restaurant.getCategories())
//                .images(restaurant.getImages())
//                .menuCategories(restaurant.getMenuCategories())
//                .tags(restaurant.getTags())
//                .build();
    }
}
