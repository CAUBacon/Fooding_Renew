package teaspoon.fooding.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopLikeCountResult {
    Long shopId;
    Long likeCount;

    @Builder
    public ShopLikeCountResult(Long shopId, Long likeCount) {
        this.shopId = shopId;
        this.likeCount = likeCount;
    }
}
