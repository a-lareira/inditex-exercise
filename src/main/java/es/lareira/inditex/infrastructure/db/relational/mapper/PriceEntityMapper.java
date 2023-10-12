package es.lareira.inditex.infrastructure.db.relational.mapper;

import es.lareira.inditex.application.domain.model.price.Price;
import es.lareira.inditex.infrastructure.db.relational.entity.PriceEntity;
import java.math.BigDecimal;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

  @Mapping(target = "finalPrice", source = "price")
  @Mapping(target = "brandId", source = "brandEntity.id")
  @Mapping(target = "rateId", source = "priceList")
  @Mapping(target = "applicationDateRange.from", source = "startDate")
  @Mapping(target = "applicationDateRange.to", source = "endDate")
  Price toDomain(PriceEntity priceEntity);

  List<Price> toDomain(List<PriceEntity> priceEntities);
}
