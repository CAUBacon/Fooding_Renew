package teaspoon.fooding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import teaspoon.fooding.domain.shop.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    @Query("select count(sl) from ShopLike sl where sl.user = :shopId")
    Long countNumberOfLike(Long shopId);
}
