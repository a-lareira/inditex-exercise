package es.lareira.inditex.adapter.api;

import es.lareira.inditex.adapter.api.mapper.PriceMapper;
import es.lareira.inditex.application.domain.model.price.AppliedPriceRequest;
import es.lareira.inditex.application.domain.model.price.Price;
import es.lareira.inditex.application.port.input.service.PriceService;
import es.lareira.inditex.generated.api.AppliedPriceApi;
import es.lareira.inditex.generated.model.AppliedPrice;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AppliedPriceController implements AppliedPriceApi {

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
