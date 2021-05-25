package teaspoon.fooding.service;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import teaspoon.fooding.api.dto.CommonResult;
import teaspoon.fooding.api.dto.ListResult;
import teaspoon.fooding.api.dto.SingleResult;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

class ResponseServiceTest {

    ResponseService responseService = new ResponseService();

    @Test
    void makeSingleResponse() {
        // given
        Data data = new Data(200, "test");

        // when
        ResponseEntity<SingleResult<Data>> result = responseService.makeSingleResponse(data,
                HttpStatus.CREATED, 105);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        SingleResult<Data> body = result.getBody();
        assertThat(body).isNotNull();
        assertThat(body.isSuccess()).isTrue();
        assertThat(body.getData()).isEqualTo(data);
        assertThat(body.getCode()).isEqualTo(105);
    }

    @Test
    void makeListResponse() {
        // given
        Data data1 = new Data(200, "test1");
        Data data2 = new Data(201, "test2");
        Data data3 = new Data(202, "test3");

        // when
        ResponseEntity<ListResult<Data>> result = responseService.makeListResponse(Arrays.asList(data1, data2, data3),
                HttpStatus.OK, 102);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

        ListResult<Data> body = result.getBody();
        assertThat(body).isNotNull();
        assertThat(body.isSuccess()).isTrue();
        assertThat(body.getList().size()).isEqualTo(3);
        assertThat(body.getList()).containsExactly(data1, data2, data3);
        assertThat(body.getCode()).isEqualTo(102);
    }

    @Test
    void makeBadRequestResponse() {
        // given
        // when
        ResponseEntity<CommonResult> result = responseService.makeBadRequestResponse(103, "test is not " +
                "valid");

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        CommonResult body = result.getBody();
        assertThat(body).isNotNull();
        assertThat(body.isSuccess()).isFalse();
        assertThat(body.getCode()).isEqualTo(103);
        assertThat(body.getMsg()).isEqualTo("test is not valid");
    }

    @Test
    void makeSuccessResponse() {
       // given
       // when
        ResponseEntity<CommonResult> result =
                responseService.makeSuccessResponse(HttpStatus.ACCEPTED, 303);

       // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);

        CommonResult body = result.getBody();
        assertThat(body).isNotNull();
        assertThat(body.isSuccess()).isTrue();
        assertThat(body.getCode()).isEqualTo(303);
        assertThat(body.getMsg()).isEqualTo("");
    }

    @Test
    void makeFailResponse() {
        // given
        // when
        ResponseEntity<CommonResult> result = responseService.makeFailResponse(HttpStatus.NOT_ACCEPTABLE, 409, "권한이" +
                " 없습니다");

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_ACCEPTABLE);

        CommonResult body = result.getBody();
        assertThat(body).isNotNull();
        assertThat(body.isSuccess()).isFalse();
        assertThat(body.getCode()).isEqualTo(409);
        assertThat(body.getMsg()).isEqualTo("권한이 없습니다");
    }

    @Test
    void makeInternalErrorResponse() {
        // given
        // when
        ResponseEntity<CommonResult> result = responseService.makeInternalErrorResponse(503, "요청이" +
                " 너무 몰려서 처리할 수 없습니다");

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

        CommonResult body = result.getBody();
        assertThat(body).isNotNull();
        assertThat(body.isSuccess()).isFalse();
        assertThat(body.getCode()).isEqualTo(503);
        assertThat(body.getMsg()).isEqualTo("요청이 너무 몰려서 처리할 수 없습니다");
    }

    static class Data {
        int code;
        String msg;

        public Data(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }

}
