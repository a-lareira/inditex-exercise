package es.lareira.inditex.adapter.api;

import es.lareira.inditex.adapter.api.mapper.PriceMapper;
import es.lareira.inditex.application.domain.model.price.AppliedPriceRequest;
import es.lareira.inditex.application.domain.model.price.Price;
import es.lareira.inditex.application.port.input.service.PriceService;
import es.lareira.inditex.generated.api.PriceApi;
import es.lareira.inditex.generated.model.AppliedPrice;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class AppliedPriceController implements PriceApi {

  private final PriceService priceService;

  private final PriceMapper priceMapper;

  @Override
  public AppliedPrice getAppliedPrice(Integer brandId, Integer productId,
      LocalDateTime applicationDate) {
    AppliedPriceRequest request = AppliedPriceRequest.builder()
        .brandId(brandId)
        .productId(productId)
        .applicationDate(applicationDate)
        .build();
    Price appliedPrice = priceService.getAppliedPrice(request);
    return priceMapper.toDTO(appliedPrice);
  }
}
