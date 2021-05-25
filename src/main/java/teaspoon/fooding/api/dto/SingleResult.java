package teaspoon.fooding.api.dto;

import lombok.Getter;

@Getter
public class SingleResult<T> extends CommonResult {
    private final T data;

    public SingleResult(boolean success, int code, T data) {
        super(success, code, "");
        this.data = data;
    }
}
