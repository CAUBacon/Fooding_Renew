package teaspoon.fooding.api.advice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import teaspoon.fooding.api.advice.exception.*;
import teaspoon.fooding.api.dto.CommonResult;
import teaspoon.fooding.service.ResponseService;

import java.nio.file.AccessDeniedException;

@RequiredArgsConstructor
@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    private final ResponseService responseService;

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<CommonResult> defaultException(Exception e) {
        System.out.println(e);
        e.printStackTrace();
        return responseService.makeInternalErrorResponse(500, "서버 오류");
    }

    @ExceptionHandler(CAuthEmailNotFoundException.class)
    protected ResponseEntity<CommonResult> emailNotFoundException(CAuthEmailNotFoundException e) {
        return responseService.makeFailResponse(HttpStatus.NOT_FOUND, 4011, "이메일 또는 비밀번호가 일치하지 않습니다");
    }

    @ExceptionHandler(CPasswordNotMatchException.class)
    protected ResponseEntity<CommonResult> passwordNotMatchException(CPasswordNotMatchException e) {
        return responseService.makeFailResponse(HttpStatus.NOT_FOUND, 4012, "이메일 또는 비밀번호가 일치하지 않습니다");
    }

    @ExceptionHandler(CAuthenticationEntryPointException.class)
    protected ResponseEntity<CommonResult> authenticationEntryPointException(CAuthenticationEntryPointException e) {
        return responseService.makeFailResponse(HttpStatus.UNAUTHORIZED, 4010, "해당 리소스에 접근하기 위한 권한이 없습니다");
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<CommonResult> accessDeniedException(AccessDeniedException e) {
        return responseService.makeFailResponse(HttpStatus.FORBIDDEN, 4030, "해당 리소스에 접근할 수 없습니다");
    }

    @ExceptionHandler(CEmailDuplicatedException.class)
    protected ResponseEntity<CommonResult> emailDuplicatedException(CEmailDuplicatedException e) {
        return responseService.makeFailResponse(HttpStatus.BAD_REQUEST, 4001, "해당 이메일로 가입한 사용자가 이미 존재합니다");
    }

    @ExceptionHandler(CNicknameDuplicatedException.class)
    protected ResponseEntity<CommonResult> nicknameDuplicatedException(CNicknameDuplicatedException e) {
        return responseService.makeFailResponse(HttpStatus.BAD_REQUEST, 4002, "해당 닉네임으로 가입한 사용자가 이미 존재합니다");
    }

    @ExceptionHandler(CRestaurantNotFoundException.class)
    protected ResponseEntity<CommonResult> restaurantNotFoundException(CRestaurantNotFoundException e) {
        return responseService.makeFailResponse(HttpStatus.NOT_FOUND, 4040, "가게를 찾을 수 없습니다");
    }

    @ExceptionHandler(CShopNotFoundException.class)
    protected ResponseEntity<CommonResult> shopNotFoundException(CShopNotFoundException e) {
        return responseService.makeFailResponse(HttpStatus.NOT_FOUND, 4041, "가게를 찾을 수 없습니다");
    }

    @ExceptionHandler(CShopLikeDuplicatedException.class)
    protected ResponseEntity<CommonResult> shopLikeDuplicatedException(CShopLikeDuplicatedException e) {
        return responseService.makeFailResponse(HttpStatus.BAD_REQUEST, 4003, "이미 좋아요를 누른 가게입니다");
    }
}
