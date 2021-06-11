package teaspoon.fooding.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import teaspoon.fooding.domain.shop.Restaurant;

import java.util.List;
import java.util.Optional;

import static teaspoon.fooding.domain.shop.QRestaurant.restaurant;

@RequiredArgsConstructor
public class RestaurantRepositoryImpl implements RestaurantRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Restaurant> inquireRestaurantDetails(Long id) {
        List<Restaurant> result = this.jpaQueryFactory
                .select(restaurant)
                .from(restaurant)
                .innerJoin(restaurant.school).fetchJoin()
                .leftJoin(restaurant.categories).fetchJoin()
                .where(restaurant.id.eq(id))
                .fetch();

        if (result.size() > 0) {
            return Optional.of(result.get(0));
        } else {
            return Optional.empty();
        }
    }
}
