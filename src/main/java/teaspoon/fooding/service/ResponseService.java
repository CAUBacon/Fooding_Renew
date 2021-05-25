package teaspoon.fooding.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import teaspoon.fooding.api.dto.CommonResult;
import teaspoon.fooding.api.dto.ListResult;
import teaspoon.fooding.api.dto.SingleResult;

import java.util.List;

@Service
public class ResponseService {

    /**
     * 단일 데이터 응답
     *
     * @param data         데이터
     * @param status       Http Code
     * @param responseCode 응답 코드
     * @param <T>          데이터 타입
     * @return ResponseEntity<SingleResult < T>>
     */
    public <T> ResponseEntity<SingleResult<T>> makeSingleResponse(T data, HttpStatus status, int responseCode) {
        return ResponseEntity
                .status(status)
                .body(new SingleResult<>(true, responseCode, data));
    }

    /**
     * 리스트 데이터 응답
     *
     * @param data         데이터
     * @param status       HttpCode
     * @param responseCode 응답 코드
     * @param <T>          데이터 타입
     * @return ResponseEntity<ListResult < T>>
     */
    public <T> ResponseEntity<ListResult<T>> makeListResponse(List<T> data, HttpStatus status, int responseCode) {
        return ResponseEntity
                .status(status)
                .body(new ListResult<>(true, responseCode, data));
    }

    public ResponseEntity<CommonResult> makeBadRequestResponse(int responseCode, String msg) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new CommonResult(false, responseCode, msg));
    }

    /**
     * 단순 성공 응답
     * @param status Http 코드
     * @param responseCode 응답 코드
     * @return ResponseEntity<CommonResult>
     */
    public ResponseEntity<CommonResult> makeSuccessResponse(HttpStatus status, int responseCode) {
        return ResponseEntity
                .status(status)
                .body(new CommonResult(true, responseCode, ""));
    }

    /**
     * 실패 응답 : success = false
     *
     * @param status       Http 코드
     * @param responseCode 응답 코드
     * @param msg          에러 메시지
     * @return ResponseEntity<CommonResult>
     */
    public ResponseEntity<CommonResult> makeFailResponse(HttpStatus status, int responseCode, String msg) {
        return ResponseEntity
                .status(status)
                .body(new CommonResult(false, responseCode, msg));
    }

    /**
     * 500 에러 응답
     *
     * @param responseCode 응답 코드
     * @param msg 에러 메시지
     * @return ResponseEntity<CommonResult>
     */
    public ResponseEntity<CommonResult> makeInternalErrorResponse(int responseCode, String msg) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new CommonResult(false, responseCode, msg));
    }
}
