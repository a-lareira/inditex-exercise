package es.lareira.inditex.adapter.api.filter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.micrometer.tracing.Tracer;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TraceInfoFilterTest {
  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private Tracer tracer;
  @InjectMocks
  private TraceInfoFilter traceInfoFilter;

  @Test
  void when_doFilter_then_add_trace_headers() throws ServletException, IOException {
    HttpServletResponse responseMock = Mockito.mock(HttpServletResponse.class);
    FilterChain filterChainMock = Mockito.mock(FilterChain.class);
    ServletRequest servletRequestMock = Mockito.mock(ServletRequest.class);
    when(tracer.currentSpan().context().traceId()).thenReturn("traceId");
    when(tracer.currentSpan().context().spanId()).thenReturn("spanId");

    traceInfoFilter.doFilter(servletRequestMock, responseMock, filterChainMock);
    verify(responseMock).addHeader("X-B3-TraceId", "traceId");
    verify(responseMock).addHeader("X-B3-SpanId", "spanId");
    verify(filterChainMock).doFilter(servletRequestMock, responseMock);
  }
}