package teaspoon.fooding.api.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import teaspoon.fooding.api.config.security.JwtTokenProvider;
import teaspoon.fooding.api.dto.LocalUserSignInDto;
import teaspoon.fooding.api.dto.LocalUserSignUpDto;
import teaspoon.fooding.api.dto.LoginInUserResponseDto;
import teaspoon.fooding.api.dto.SingleResult;
import teaspoon.fooding.domain.user.LocalUser;
import teaspoon.fooding.domain.user.UserRole;
import teaspoon.fooding.service.LocalUserAuthService;
import teaspoon.fooding.service.ResponseService;

import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
@Slf4j
@RestController
public class LocalAuthController {
    private final LocalUserAuthService localUserAuthService;
    private final JwtTokenProvider jwtTokenProvider;
    private final ResponseService responseService;

    @ApiOperation(value = "로컬 회원 가입", notes = "이메일과 비밀번호로 회원가입을 한다")
    @PostMapping("/local/signUp")
    public ResponseEntity<SingleResult<LoginInUserResponseDto>> signUp(@RequestBody LocalUserSignUpDto dto) {
        LocalUser user = localUserAuthService.signUp(dto);
        LoginInUserResponseDto response = LoginInUserResponseDto.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .gender(user.getGender())
                .token(jwtTokenProvider.issueToken(user.getId().toString(),
                        user.getRoles().stream().map(UserRole::getRole).collect(toList())))
                .build();

        return responseService.makeSingleResponse(response, HttpStatus.CREATED, 201);
    }

    @ApiOperation(value = "로컬 로그인", notes = "이메일과 비밀번호로 로그인을 한다")
    @PostMapping("/local/signIn")
    public ResponseEntity<SingleResult<LoginInUserResponseDto>> signIn(@RequestBody LocalUserSignInDto dto) {
        LocalUser user = localUserAuthService.signIn(dto);
        LoginInUserResponseDto response = LoginInUserResponseDto.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .gender(user.getGender())
                .token(jwtTokenProvider.issueToken(user.getId().toString(),
                        user.getRoles().stream().map(UserRole::getRole).collect(toList())))
                .build();

        return responseService.makeSingleResponse(response, HttpStatus.OK, 200);
    }
}
