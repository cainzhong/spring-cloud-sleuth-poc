package com.smax.service;

import lombok.Value;
import org.slf4j.MDC;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * TODO
 *
 * @author zhongtao
 * @since 9/28/2022
 */
@Service
public class RestTemplateService {
  @Bean
  public RestTemplate getRestTemplate() {
    return new RestTemplate();
  }
}
