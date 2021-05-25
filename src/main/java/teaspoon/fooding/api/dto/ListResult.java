package teaspoon.fooding.api.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ListResult<T> extends CommonResult {
    private final List<T> list;

    public ListResult(boolean success, int code, List<T> list) {
        super(success, code, "");
        this.list = list;
    }
}
