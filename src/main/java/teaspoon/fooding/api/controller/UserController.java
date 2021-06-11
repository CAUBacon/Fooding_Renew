package teaspoon.fooding.api.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teaspoon.fooding.domain.shop.Shop;
import teaspoon.fooding.domain.user.User;
import teaspoon.fooding.service.ShopService;
import teaspoon.fooding.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final ShopService shopService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "x-auth-token", value = "로그인 토큰", required = true, dataType = "String",
                    paramType = "header")
    })
    @ApiOperation(value = "사용자가 가게를 좋아요 누르는 엔드포인트", notes = "가게 좋아요")
    @PostMapping("/shop/{shopId}/like")
    public void likeShop(@AuthenticationPrincipal User user,
                         @ApiParam(value = "가게 id", required = true) @PathVariable Long shopId) {
        Shop shop = shopService.findShopById(shopId);
        userService.likeShop(user, shop);
    }
}
