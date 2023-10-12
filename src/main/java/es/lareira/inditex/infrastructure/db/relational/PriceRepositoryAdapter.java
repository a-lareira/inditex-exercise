package es.lareira.inditex.infrastructure.db.relational;

import es.lareira.inditex.application.domain.model.price.Price;
import es.lareira.inditex.application.port.output.repository.PriceRepository;
import es.lareira.inditex.infrastructure.db.relational.entity.PriceEntity;
import es.lareira.inditex.infrastructure.db.relational.jpa.JpaPriceRepository;
import es.lareira.inditex.infrastructure.db.relational.mapper.PriceEntityMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepository {

  private final JpaPriceRepository jpaPriceRepository;
  private final PriceEntityMapper priceMapper;

  @Override
  public List<Price> findPricesByProductIdAndBrandId(Long productId, Long brandId) {
    List<PriceEntity> entities = jpaPriceRepository.findByProductIdAndBrandEntity_Id(
        productId, brandId);
    return priceMapper.toDomain(entities);
  }
}
