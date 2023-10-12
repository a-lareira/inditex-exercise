package es.lareira.inditex.adapter.api.advice;

import es.lareira.inditex.application.domain.exception.PriceNotFoundException;
import es.lareira.inditex.generated.model.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {


  public static final String UNKNOWN_ERROR = "Unknown error";
  public static final String NO_PRICE_FOUND_ERROR = "No price found for requested criteria";

  @ExceptionHandler(PriceNotFoundException.class)
  ResponseEntity<Error> handlePriceNotFoundException() {
    Error error = new Error()
        .code(404)
        .message(NO_PRICE_FOUND_ERROR);
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  @ExceptionHandler(Exception.class)
  ResponseEntity<Error> handleUnknownException(Exception e) {
    log.error(UNKNOWN_ERROR, e);
    Error error = new Error()
        .code(500)
        .message(UNKNOWN_ERROR);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
  }
}
