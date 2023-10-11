package es.lareira.inditex.adapter.api;

import es.lareira.inditex.generated.api.AppliedPriceApi;
import es.lareira.inditex.generated.model.AppliedPrice;
import java.time.OffsetDateTime;
import org.springframework.web.bind.annotation.RestController;


public class AppliedPriceController implements AppliedPriceApi {

  @Override
  public AppliedPrice getAppliedPrice(Integer brandId, Integer productId,
      OffsetDateTime fromApplicationDate, OffsetDateTime toApplicationDate) {
    throw new RuntimeException("Not implemented");
  }
}
