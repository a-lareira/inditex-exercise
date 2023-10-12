package es.lareira.inditex.infrastructure.db.relational.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "PRICES")
@AllArgsConstructor
@NoArgsConstructor
public class PriceEntity {

  @Id
  @Column(name = "PRICE_ID")
  private Long id;
  @ManyToOne
  @JoinColumn(name = "BRAND_ID", nullable = false)
  private BrandEntity brandEntity;
  @Column(name = "START_DATE")
  private LocalDateTime startDate;
  @Column(name = "END_DATE")
  private LocalDateTime endDate;
  @Column(name = "PRICE_LIST")
  private Long priceList;
  @Column(name = "PRODUCT_ID")
  private Long productId;
  @Column(name = "PRIORITY")
  private Integer priority;
  @Column(name = "PRICE")
  private BigDecimal price;
  @Column(name = "CURR")
  private String currency;

}
