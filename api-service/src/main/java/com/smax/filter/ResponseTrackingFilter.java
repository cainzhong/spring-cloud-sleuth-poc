package com.smax.filter;

import com.smax.controller.HomeController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * https://github.com/spring-cloud/spring-cloud-sleuth/issues/633
 *
 * @author zhongtao
 * @since 9/28/2022
 */
@Component
@Slf4j
public class ResponseTrackingFilter extends OncePerRequestFilter {

  private static final String TRACKING_HEADER = "X-TRACKING-ID";
  @Autowired
  private Tracer tracer;

  protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
                                  final FilterChain filterChain) throws ServletException, IOException {

    final Span currentSpan = tracer.currentSpan();
    if (null != currentSpan && !StringUtils.hasText(response.getHeader(TRACKING_HEADER))) {

      final String traceId = currentSpan.context().traceId();
      log.debug("Added tracking id in response - {}", traceId);
      response.setHeader(TRACKING_HEADER, traceId);
    }
    filterChain.doFilter(request, response);
  }
}
