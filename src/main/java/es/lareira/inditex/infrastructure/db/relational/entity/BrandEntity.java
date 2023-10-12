package es.lareira.inditex.infrastructure.db.relational.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "BRANDS")
public class BrandEntity {

  @Id
  @Column(name = "BRAND_ID")
  private Long id;
  @Column(name = "NAME")
  private String name;
}
