package teaspoon.fooding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teaspoon.fooding.domain.shop.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}
