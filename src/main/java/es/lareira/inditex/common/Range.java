package es.lareira.inditex.common;

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
