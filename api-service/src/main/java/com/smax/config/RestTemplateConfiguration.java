package com.smax.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * TODO
 *
 * @author zhongtao
 * @since 9/30/2022
 */
@Configuration
public class RestTemplateConfiguration {
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
