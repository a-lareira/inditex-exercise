package es.lareira.inditex.adapter.api.advice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ApiExceptionHandlerTest {

  private ApiExceptionHandler apiExceptionHandler;

  @BeforeEach
  void setUp() {
    apiExceptionHandler = new ApiExceptionHandler();
  }

  @Test
  void when_handle_price_not_found_exception_then_return_error() {
    var error = apiExceptionHandler.handlePriceNotFoundException();
    assertNotNull(error.getBody());
    assertEquals(404, error.getBody().getCode());
    assertEquals("No price found for requested criteria", error.getBody().getMessage());
  }

  @Test
  void when_handle_unknown_exception_then_return_error() {
    var error = apiExceptionHandler.handleUnknownException(new Exception());
    assertNotNull(error.getBody());
    assertEquals(500, error.getBody().getCode());
    assertEquals("Unknown error", error.getBody().getMessage());
  }
}