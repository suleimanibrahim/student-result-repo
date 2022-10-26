package com.example.schoolmanagementsystem.respose;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@RequiredArgsConstructor
public class BaseResponse<T> {
    private HttpStatus responseCode;
    private String responseMessage;
    private T responseContent;
}
