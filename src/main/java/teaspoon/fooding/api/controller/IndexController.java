package teaspoon.fooding.api.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teaspoon.fooding.domain.user.UserRole;

@RequiredArgsConstructor
@Api(tags = {"Home"})
@RestController
@RequestMapping("/api")
public class IndexController {

    @ApiOperation(value = "Index", notes = "컨트롤러 확인을 위한 엔드포인트")
    @GetMapping
    public String helloFoodingWorld() {
        return "Hello Fooding World!";
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "x-auth-token", value = "로그인 토큰", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "인사해주는 엔드포인트", notes = "이름을 보내면 인사를 해줘요")
    @Secured(UserRole.ROLE.COMMON)
    @GetMapping("/hello/{name}")
    public String greetUser(@ApiParam(value = "이름", required = true) @PathVariable String name) {
        return "Hello " + name + " ~!";
    }

}
