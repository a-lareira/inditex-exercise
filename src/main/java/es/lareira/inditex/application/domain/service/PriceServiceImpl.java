package es.lareira.inditex.application.domain.service;

import es.lareira.inditex.application.domain.exception.PriceNotFoundException;
import es.lareira.inditex.application.domain.model.price.AppliedPriceRequest;
import es.lareira.inditex.application.domain.model.price.Price;
import es.lareira.inditex.application.port.input.service.PriceService;
import es.lareira.inditex.application.port.output.repository.PriceRepository;
import es.lareira.inditex.common.model.Range;
import java.time.LocalDateTime;
import java.util.Comparator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

  private final PriceRepository priceRepository;

  @Override
  public Price getAppliedPrice(AppliedPriceRequest appliedPriceRequest) {
    return priceRepository.findPricesByProductIdAndBrandId(
            appliedPriceRequest.getProductId().longValue(),
            appliedPriceRequest.getBrandId().longValue())
        .stream()
        .filter(price -> isInAppliedDateRange(appliedPriceRequest, price))
        .max(Comparator.comparing(Price::getPriority))
        .orElseThrow(PriceNotFoundException::new);
  }

  private boolean isInAppliedDateRange(AppliedPriceRequest appliedPriceRequest, Price price) {
    LocalDateTime applicationDate = appliedPriceRequest.getApplicationDate();
    Range<LocalDateTime> priceApplicationDates = price.getApplicationDateRange();
    return !applicationDate.isAfter(priceApplicationDates.getTo()) &&
        !applicationDate.isBefore(priceApplicationDates.getFrom());
  }
}
