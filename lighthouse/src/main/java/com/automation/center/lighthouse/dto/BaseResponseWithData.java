package com.automation.center.lighthouse.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
class BaseResponse {
    private int code;
    private String message;
    private boolean success;
}

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BaseResponseWithData<T> extends BaseResponse {
    private T data;

    public BaseResponseWithData(T data) {
        this.data = data;
    }
}
