package es.lareira.inditex.application.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import es.lareira.inditex.application.domain.exception.PriceNotFoundException;
import es.lareira.inditex.application.domain.model.price.AppliedPriceRequest;
import es.lareira.inditex.application.domain.model.price.Price;
import es.lareira.inditex.application.port.output.repository.PriceRepository;
import es.lareira.inditex.common.model.Range;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {

  @Mock
  private PriceRepository priceRepository;
  @InjectMocks
  private PriceServiceImpl priceService;

  @Test
  void when_getAppliedPrice_then_return_not_null() {
    LocalDateTime from = LocalDateTime.of(2023, 1, 1, 0, 0, 0);
    LocalDateTime to = from.plusDays(5L);
    Price price = Price.builder()
        .price(BigDecimal.TEN)
        .applicationDateRange(Range.<LocalDateTime>builder().from(from).to(to).build())
        .build();
    when(priceRepository.findPricesByProductIdAndBrandId(1L, 2L))
        .thenReturn(Collections.singletonList(price));
    AppliedPriceRequest request = AppliedPriceRequest.builder()
        .productId(1)
        .brandId(2)
        .applicationDate(from)
        .build();
    assertNotNull(priceService.getAppliedPrice(request));
  }

  @Test
  void when_priceRepository_give_no_results_then_throws_not_found_exception() {
    LocalDateTime from = LocalDateTime.of(2023, 1, 1, 0, 0, 0);
    AppliedPriceRequest request = AppliedPriceRequest.builder()
        .brandId(1)
        .productId(5)
        .applicationDate(from)
        .build();
    assertThrows(PriceNotFoundException.class, () -> priceService.getAppliedPrice(request));
  }

  @Test
  void when_priceRepository_gives_multiple_prices_return_the_one_on_date_range() {
    LocalDateTime from = LocalDateTime.of(2023, 1, 1, 0, 0, 0);
    LocalDateTime to = from.plusDays(5L);
    AppliedPriceRequest request = AppliedPriceRequest.builder()
        .productId(7)
        .brandId(8)
        .applicationDate(from)
        .build();
    Price price1 = Price.builder()
        .price(BigDecimal.TEN)
        .applicationDateRange(
            Range.<LocalDateTime>builder().from(from.minusYears(3)).to(to.minusYears(3)).build())
        .build();
    Price price2 = Price.builder()
        .price(BigDecimal.TEN)
        .applicationDateRange(Range.<LocalDateTime>builder().from(from).to(to).build())
        .build();
    List<Price> savedPrices = List.of(price1, price2);
    when(priceRepository.findPricesByProductIdAndBrandId(7L, 8L))
        .thenReturn(savedPrices);
    Price result = priceService.getAppliedPrice(request);
    assertEquals(price2, result);
  }

  @Test
  void when_priceRepository_give_multiple_prices_that_apply_on_date_then_return_the_one_with_highest_order() {
    LocalDateTime from = LocalDateTime.of(2023, 1, 1, 0, 0, 0);
    LocalDateTime to = from.plusDays(5L);
    AppliedPriceRequest request = AppliedPriceRequest.builder()
        .productId(7)
        .brandId(8)
        .applicationDate(from)
        .build();
    Price price1 = Price.builder()
        .price(BigDecimal.TEN)
        .applicationDateRange(Range.<LocalDateTime>builder().from(from).to(to).build())
        .priority(0)
        .build();
    Price price2 = Price.builder()
        .price(BigDecimal.TEN)
        .applicationDateRange(Range.<LocalDateTime>builder().from(from).to(to).build())
        .priority(1)
        .build();
    List<Price> savedPrices = List.of(price1, price2);
    when(priceRepository.findPricesByProductIdAndBrandId(7L, 8L))
        .thenReturn(savedPrices);
    Price result = priceService.getAppliedPrice(request);
    assertEquals(price2, result);
  }
}