package es.lareira.inditex.application.domain.model.price;

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

  private LocalDateTime applicationDate;
  private Integer productId;
  private Integer brandId;
}
