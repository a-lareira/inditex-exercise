package es.lareira.inditex.adapter.api.mapper;

import es.lareira.inditex.application.domain.model.price.Price;
import es.lareira.inditex.generated.model.AppliedPrice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceMapper {

  @Mapping(target = "rateCode", source = "rateId")
  @Mapping(target = "productCode", source = "productId")
  @Mapping(target = "fromApplicationDate", source = "applicationDateRange.from")
  @Mapping(target = "toApplicationDate", source = "applicationDateRange.to")
  @Mapping(target = "finalPrice", source = "price")
  @Mapping(target = "brandCode", source = "brandId")
  AppliedPrice toDTO(Price price);
}
