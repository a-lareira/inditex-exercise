package es.lareira.inditex.common.model;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Data
public class Range<T> {

  private final T from;
  private final T to;
}
