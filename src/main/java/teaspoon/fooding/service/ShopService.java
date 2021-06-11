package teaspoon.fooding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teaspoon.fooding.api.advice.exception.CShopNotFoundException;
import teaspoon.fooding.domain.shop.Shop;
import teaspoon.fooding.repository.ShopRepository;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ShopService {

    private final ShopRepository shopRepository;

    public Shop findShopById(Long shopId) {
        return shopRepository.findById(shopId).orElseThrow(CShopNotFoundException::new);
    }
}
