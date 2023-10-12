package es.lareira.inditex.infrastructure.db.relational.jpa;

import es.lareira.inditex.infrastructure.db.relational.entity.PriceEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPriceRepository extends CrudRepository<PriceEntity, Long> {

  List<PriceEntity> findByProductIdAndBrandEntity_Id(Long productId, Long brandId);
}
