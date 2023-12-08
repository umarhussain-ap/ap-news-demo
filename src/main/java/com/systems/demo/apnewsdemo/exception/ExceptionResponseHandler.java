package com.systems.demo.apnewsdemo.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/** The type Exception response handler. */
@ControllerAdvice
public class ExceptionResponseHandler {

  /**
   * Handle method argument not valid response entity.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            error -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handle service exception response entity.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ExceptionHandler(ServiceException.class)
  public ResponseEntity<Object> handleServiceException(ServiceException ex) {

    if (!CollectionUtils.isEmpty(ex.getErrors())) {
      return new ResponseEntity<>(ex.getErrors(), ex.getHttpStatus());
    } else {
      return new ResponseEntity<>(ex.getMessage(), ex.getHttpStatus());
    }
  }
}
