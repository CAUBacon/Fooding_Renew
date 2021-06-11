package teaspoon.fooding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teaspoon.fooding.domain.shop.Shop;
import teaspoon.fooding.domain.shop.ShopLike;
import teaspoon.fooding.domain.user.User;

import java.util.List;

public interface ShopLikeRepository extends JpaRepository<ShopLike, Long> {
    List<ShopLike> findByUser(User user);

    List<ShopLike> findByUserAndShop(User user, Shop shop);

    long countByShop(Shop shop);

    boolean existsByUserAndShop(User user, Shop shop);
}
