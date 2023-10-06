package es.lareira.inditex.application.domain.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import es.lareira.inditex.application.domain.model.price.AppliedPriceRequest;
import es.lareira.inditex.application.port.output.repository.PriceRepository;
import es.lareira.inditex.common.Range;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jmx.export.annotation.ManagedOperation;

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
    AppliedPriceRequest request = AppliedPriceRequest.builder()
        .brandId(1L)
        .brandId(2L)
        .applicationDateRange(Range.<LocalDateTime>builder().from(from).to(to).build())
        .build();
    assertNotNull(priceService.getAppliedPrice(request));
  }

  @Test
  void when_priceRepository_give_no_results_then_throws_not_found_exception() {
    LocalDateTime from = LocalDateTime.of(2023, 1, 1, 0, 0, 0);
    LocalDateTime to = from.plusDays(5L);
    AppliedPriceRequest request = AppliedPriceRequest.builder()
        .brandId(3L)
        .brandId(4L)
        .applicationDateRange(Range.<LocalDateTime>builder().from(from).to(to).build())
        .build();
    assertNotNull(priceService.getAppliedPrice(request));

  }
}