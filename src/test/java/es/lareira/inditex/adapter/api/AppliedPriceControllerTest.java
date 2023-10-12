package es.lareira.inditex.adapter.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import es.lareira.inditex.adapter.api.mapper.PriceMapper;
import es.lareira.inditex.application.domain.model.price.AppliedPriceRequest;
import es.lareira.inditex.application.domain.model.price.Price;
import es.lareira.inditex.application.port.input.service.PriceService;
import es.lareira.inditex.generated.model.AppliedPrice;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AppliedPriceControllerTest {

  @Mock
  private PriceService priceService;
  @Mock
  private PriceMapper priceMapper;
  @InjectMocks
  private AppliedPriceController appliedPriceController;

  @Test
  void when_getAppliedPrice_ensure_service_is_called_and_result_mapped() {
    when(priceService.getAppliedPrice(any()))
        .thenReturn(new Price());
    when(priceMapper.toDTO(any()))
        .thenReturn(new AppliedPrice());
    LocalDateTime now = LocalDateTime.now();
    AppliedPrice result = appliedPriceController.getAppliedPrice(1, 2, now);
    assertNotNull(result);
    ArgumentCaptor<AppliedPriceRequest> appliedPriceCaptor = ArgumentCaptor.forClass(
        AppliedPriceRequest.class);
    verify(priceService).getAppliedPrice(appliedPriceCaptor.capture());
    assertEquals(1, appliedPriceCaptor.getValue().getBrandId());
    assertEquals(2, appliedPriceCaptor.getValue().getProductId());
    assertEquals(now, appliedPriceCaptor.getValue().getApplicationDate());
  }

}