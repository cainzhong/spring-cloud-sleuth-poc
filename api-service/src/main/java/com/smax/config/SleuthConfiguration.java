package com.smax.config;

import brave.baggage.BaggageField;
import brave.baggage.CorrelationScopeConfig;
import brave.context.slf4j.MDCScopeDecorator;
import brave.propagation.CurrentTraceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author zhongtao
 * @since 9/28/2022
 */
@Configuration
public class SleuthConfiguration {

  @Bean
  BaggageField userIdField() {
    return BaggageField.create("user-id");
  }

  @Bean
  CurrentTraceContext.ScopeDecorator mdcScopeDecorator() {
    return MDCScopeDecorator.newBuilder().clear()
      .add(CorrelationScopeConfig
        .SingleCorrelationField
        .newBuilder(userIdField())
        .flushOnUpdate()
        .build())
      .build();
  }
}
