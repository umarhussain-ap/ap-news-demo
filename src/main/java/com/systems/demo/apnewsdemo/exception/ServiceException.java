package com.systems.demo.apnewsdemo.exception;

import com.systems.demo.apnewsdemo.dto.response.ErrorDto;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/** The type Service exception. */
@Getter
@Setter
@RequiredArgsConstructor
public class ServiceException extends RuntimeException {
  private final String message;
  private final transient List<ErrorDto> errors;
  private final HttpStatus httpStatus;

  /**
   * Of service exception.
   *
   * @param message the message
   * @param errors the errors
   * @param httpStatus the http status
   * @return the service exception
   */
  public static ServiceException of(String message, List<ErrorDto> errors, HttpStatus httpStatus) {
    return new ServiceException(message, errors, httpStatus);
  }
}
