package teaspoon.fooding.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommonResult {
    @ApiModelProperty(value = "응답 성공 여부 : true/false")
    private boolean success;

    @ApiModelProperty(value = "응답 코드 번호")
    private int code;

    @ApiModelProperty(value ="응답 메시지")
    private String msg;

    public CommonResult(boolean success, int code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }
}
