package teaspoon.fooding.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teaspoon.fooding.api.advice.exception.CAuthenticationEntryPointException;
import teaspoon.fooding.api.dto.CommonResult;

import java.nio.file.AccessDeniedException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping("/entrypoint")
    public ResponseEntity<CommonResult> entrypointException() {
        throw new CAuthenticationEntryPointException();
    }

    @GetMapping("/accessdenied")
    public ResponseEntity<CommonResult> accessDeniedException() throws AccessDeniedException {
        throw new AccessDeniedException("");
    }
}
