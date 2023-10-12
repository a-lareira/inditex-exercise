package es.lareira.inditex.application.domain.model.price;

import es.lareira.inditex.common.Range;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppliedPriceRequest {

  private Range<LocalDateTime> applicationDateRange;
  private Long productId;
  private Long brandId;
}
