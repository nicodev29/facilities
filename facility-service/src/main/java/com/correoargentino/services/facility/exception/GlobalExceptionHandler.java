package com.correoargentino.services.facility.exception;

import com.correoargentino.services.facility.response.ErrorResponse;
import java.net.URI;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * GlobalExceptionHandler.
 */
@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

  @ExceptionHandler(FacilityNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleException(FacilityNotFoundException exception) {
    log.error(exception.getMessage(), exception);

    return new ResponseEntity<>(
        new ErrorResponse("Facility Not Found", exception.getMessage(),
            HttpStatus.NOT_FOUND.value(), LocalDateTime.now(),
            URI.create(ServletUriComponentsBuilder
                .fromCurrentRequestUri().toUriString())), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(FacilityAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handleException(FacilityAlreadyExistsException exception) {
    log.error(exception.getMessage(), exception);

    return new ResponseEntity<>(
        new ErrorResponse("Facility Already Exists", exception.getMessage(),
            HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(),
            URI.create(ServletUriComponentsBuilder
                .fromCurrentRequestUri().toUriString())), HttpStatus.BAD_REQUEST);
  }

  /**
   * handleException.
   */
  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponse> handleException(RuntimeException exception) {
    log.error(exception.getMessage(), exception);

    return new ResponseEntity<>(
        new ErrorResponse("Internal Server Error", exception.getMessage(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now(),
            URI.create(ServletUriComponentsBuilder
                .fromCurrentRequestUri().toUriString())), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
