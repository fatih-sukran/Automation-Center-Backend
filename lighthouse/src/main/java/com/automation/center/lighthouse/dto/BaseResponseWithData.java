package com.automation.center.lighthouse.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
class BaseResponse {
    private int code;
    private String message;
    private boolean success;
}

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseResponseWithData<T> extends BaseResponse {
    private T data;
}
