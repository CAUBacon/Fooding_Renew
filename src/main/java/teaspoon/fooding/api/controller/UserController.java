package teaspoon.fooding.api.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import teaspoon.fooding.api.dto.ShopLikeCountResult;
import teaspoon.fooding.api.dto.SingleResult;
import teaspoon.fooding.domain.shop.Shop;
import teaspoon.fooding.domain.user.User;
import teaspoon.fooding.service.ResponseService;
import teaspoon.fooding.service.ShopService;
import teaspoon.fooding.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final ShopService shopService;
    private final ResponseService responseService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "x-auth-token", value = "로그인 토큰", required = true, dataType = "String",
                    paramType = "header")
    })
    @ApiOperation(value = "사용자가 가게를 좋아요 누르는 엔드포인트", notes = "가게 좋아요")
    @PostMapping("/shop/{shopId}/like")
    public ResponseEntity<SingleResult<ShopLikeCountResult>> likeShop(@AuthenticationPrincipal User user,
                                                                      @ApiParam(value = "가게 id", required = true) @PathVariable Long shopId) {
        Shop shop = shopService.findShopById(shopId);
        long likeCount = userService.likeShop(user, shop);
        ShopLikeCountResult result = ShopLikeCountResult.builder()
                .shopId(shopId)
                .likeCount(likeCount)
                .build();
        return responseService.makeSingleResponse(result, HttpStatus.CREATED, 201);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "x-auth-token", value = "로그인 토큰", required = true, dataType = "String",
                    paramType = "header")
    })
    @ApiOperation(value = "사용자가 가게를 좋아요 취소하는 엔드포인트", notes = "가게 좋아요 취소")
    @DeleteMapping("/shop/{shopId}/like")
    public ResponseEntity<SingleResult<ShopLikeCountResult>> unlikeShop(@AuthenticationPrincipal User user,
                                                                        @ApiParam(value = "가게 id", required = true) @PathVariable Long shopId) {
        Shop shop = shopService.findShopById(shopId);
        long likeCount = userService.unlikeShop(user, shop);
        ShopLikeCountResult result = ShopLikeCountResult.builder()
                .shopId(shopId)
                .likeCount(likeCount)
                .build();
        return responseService.makeSingleResponse(result, HttpStatus.CREATED, 201);
    }
}
