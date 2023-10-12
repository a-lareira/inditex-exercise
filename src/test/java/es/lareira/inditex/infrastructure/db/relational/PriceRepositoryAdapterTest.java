package es.lareira.inditex.infrastructure.db.relational;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import es.lareira.inditex.application.domain.model.price.Price;
import es.lareira.inditex.infrastructure.db.relational.entity.PriceEntity;
import es.lareira.inditex.infrastructure.db.relational.jpa.JpaPriceRepository;
import es.lareira.inditex.infrastructure.db.relational.mapper.PriceEntityMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceRepositoryAdapterTest {

  @Mock
  private JpaPriceRepository jpaPriceRepository;
  @Mock
  private PriceEntityMapper priceMapper;
  @InjectMocks
  private PriceRepositoryAdapter priceRepositoryAdapter;

  @Test
  void when_findPricesByProductIdAndBrandId_then_should_call_jpaPriceRepository() {
    Long productId = 1L;
    Long brandId = 1L;
    when(jpaPriceRepository.findByProductIdAndBrandEntity_Id(productId, brandId))
        .thenReturn(List.of(new PriceEntity(), new PriceEntity()));
    when(priceMapper.toDomain(anyList()))
        .thenReturn(List.of(new Price(), new Price()));
    List<Price> prices = priceRepositoryAdapter.findPricesByProductIdAndBrandId(productId, brandId);
    assertEquals(2, prices.size());
    ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);
    verify(priceMapper).toDomain(captor.capture());
    assertEquals(2, captor.getValue().size());
  }
}