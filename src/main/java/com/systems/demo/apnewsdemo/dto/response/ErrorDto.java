package com.systems.demo.apnewsdemo.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class ErrorDto {
    private final String errorMessage;
    private final String errorCode;
}
