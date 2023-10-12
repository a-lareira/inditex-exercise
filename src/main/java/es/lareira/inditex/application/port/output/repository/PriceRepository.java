package es.lareira.inditex.application.port.output.repository;

import es.lareira.inditex.application.domain.model.price.Price;
import java.util.List;

public interface PriceRepository {

  List<Price> findPricesByProductIdAndBrandId(Long productId, Long brandId);
}
