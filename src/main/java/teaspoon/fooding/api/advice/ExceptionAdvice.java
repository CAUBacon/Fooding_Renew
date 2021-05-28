package teaspoon.fooding.api.advice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import teaspoon.fooding.api.dto.CommonResult;
import teaspoon.fooding.service.ResponseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final ResponseService responseService;

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<CommonResult> defaultException(HttpServletRequest request, HttpServletResponse response) {
        return responseService.makeInternalErrorResponse(500, "서버 오류");
    }
}
