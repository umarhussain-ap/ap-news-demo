package com.systems.demo.apnewsdemo.exception;

import com.systems.demo.apnewsdemo.dto.response.ErrorDto;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class ServiceException extends RuntimeException {
    private final String message;
    private final transient List<ErrorDto> errors;
    private final HttpStatus httpStatus;

    public static ServiceException of(String message,List<ErrorDto> errors,HttpStatus httpStatus){
        return new ServiceException(message,errors,httpStatus);
    }
}
