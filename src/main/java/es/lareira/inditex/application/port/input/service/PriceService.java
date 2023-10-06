package es.lareira.inditex.application.port.input.service;

import es.lareira.inditex.application.domain.model.price.AppliedPriceRequest;
import es.lareira.inditex.application.domain.model.price.Price;

public interface PriceService {
  Price getAppliedPrice(AppliedPriceRequest appliedPriceRequest);

}
