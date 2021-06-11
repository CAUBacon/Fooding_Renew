package teaspoon.fooding.repository;

import teaspoon.fooding.domain.shop.Restaurant;

import java.util.Optional;

public interface RestaurantRepositoryCustom {
    Optional<Restaurant> inquireRestaurantDetails(Long id);
}
