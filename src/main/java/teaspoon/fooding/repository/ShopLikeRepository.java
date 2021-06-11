package teaspoon.fooding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teaspoon.fooding.domain.shop.Shop;
import teaspoon.fooding.domain.shop.ShopLike;
import teaspoon.fooding.domain.user.User;

import java.util.List;

public interface ShopLikeRepository extends JpaRepository<ShopLike, Long> {
    public List<ShopLike> findByUser(User user);

    public boolean existsByUserAndShop(User user, Shop shop);
}
