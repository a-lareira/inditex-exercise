package es.lareira.inditex.application.domain.service;

import es.lareira.inditex.application.domain.model.price.AppliedPriceRequest;
import es.lareira.inditex.application.domain.model.price.Price;
import es.lareira.inditex.application.port.input.service.PriceService;
import es.lareira.inditex.application.port.output.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

  private final PriceRepository priceRepository;

  @Override
  public Price getAppliedPrice(AppliedPriceRequest appliedPriceRequest) {
    return Price.builder().build();
  }
}
