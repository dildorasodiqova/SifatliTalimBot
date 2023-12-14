package com.example.bot.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T>{
    private Boolean isSuccess;
    private int code;
    private String message;
    private T data;

    public ApiResponse(Boolean isSuccess,int code, String message, T data){
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(boolean isSuccess, int code, String groupNotFound) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
