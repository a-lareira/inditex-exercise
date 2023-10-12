package es.lareira.inditex.application.domain.model.price;

import es.lareira.inditex.common.Range;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Price {

  private Long brandId;
  private Range<LocalDateTime> applicationDateRange;
  private Long rateId;
  private Long productId;
  private BigDecimal price;
  private Integer priority;
  private Currency currency;
}
