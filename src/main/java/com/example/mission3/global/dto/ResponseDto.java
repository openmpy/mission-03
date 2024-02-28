package com.example.mission3.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseDto<T> {

    private boolean status;
    private String message;
    private T data;
}
