package es.lareira.inditex.adapter.api.filter;


import io.micrometer.tracing.Span;
import io.micrometer.tracing.TraceContext;
import io.micrometer.tracing.Tracer;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TraceInfoFilter implements Filter {

  public static final String TRACE_ID_HEADER = "X-B3-TraceId";
  public static final String SPAN_ID_HEADER = "X-B3-SpanId";

  private final Tracer tracer;

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
    String traceId = Optional.of(tracer)
        .map(Tracer::currentSpan)
        .map(Span::context)
        .map(TraceContext::traceId)
        .orElse(StringUtils.EMPTY);
    String spanId = Optional.of(tracer)
        .map(Tracer::currentSpan)
        .map(Span::context)
        .map(TraceContext::spanId)
        .orElse(StringUtils.EMPTY);
    httpServletResponse.addHeader(TRACE_ID_HEADER, traceId);
    httpServletResponse.addHeader(SPAN_ID_HEADER, spanId);
    filterChain.doFilter(servletRequest, servletResponse);
  }
}