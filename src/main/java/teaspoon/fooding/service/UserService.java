package teaspoon.fooding.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teaspoon.fooding.api.advice.exception.CShopLikeDuplicatedException;
import teaspoon.fooding.domain.shop.Shop;
import teaspoon.fooding.domain.shop.ShopLike;
import teaspoon.fooding.domain.user.User;
import teaspoon.fooding.repository.ShopLikeRepository;
import teaspoon.fooding.repository.UserRepository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ShopLikeRepository shopLikeRepository;

    @Transactional
    public void likeShop(User user, Shop shop) {
        boolean didUserLikeShop = shopLikeRepository.existsByUserAndShop(user, shop);
        if (didUserLikeShop) {
            throw new CShopLikeDuplicatedException();
        }

        ShopLike shopLike = ShopLike.builder()
                .shop(shop)
                .user(user)
                .build();
        shopLikeRepository.save(shopLike);
    }

    @Transactional
    public void unlikeShop(User user, Shop shop) {
        List<ShopLike> shopLikes = shopLikeRepository.findByUserAndShop(user, shop);
        if (shopLikes.size() != 0) {
            shopLikeRepository.deleteAll(shopLikes);
        }
    }
}
