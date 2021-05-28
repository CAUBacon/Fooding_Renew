package teaspoon.fooding.api.advice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import teaspoon.fooding.api.dto.CommonResult;
import teaspoon.fooding.service.ResponseService;

@RequiredArgsConstructor
@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    private final ResponseService responseService;

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<CommonResult> defaultException(Exception e) {
        log.error("Exception", e);
        return responseService.makeInternalErrorResponse(500, "서버 오류");
    }
}
