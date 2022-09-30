package com.smax.service;

import com.smax.bean.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

/**
 * TODO
 *
 * @author zhongtao
 * @since 9/21/2022
 */
@Service
@Slf4j
public class CustomerClient {

  @Value("${spring.application.name}")
  private String springApplicationName;

  private RestTemplate restTemplate;
  private String baseUrl;

  public CustomerClient(RestTemplate restTemplate, @Value("${customerClient.baseUrl}") String baseUrl) {
    this.restTemplate = restTemplate;
    this.baseUrl = baseUrl;
  }

  /*
   *  The field user-id will be added into http header.
   *  Headers: [X-B3-TraceId:"7da50a6980360687", X-B3-SpanId:"847104a2958f8bbb", X-B3-ParentSpanId:"7da50a6980360687",
   *              X-B3-Sampled:"1", user-id:"tzhong"]
   * */
  public Customer getCustomer(@PathVariable("id") long id) {
    String url = String.format("%s/customers/%d", baseUrl, id);
    return restTemplate.getForObject(url, Customer.class);
  }

  public String printHello() {
    log.info("print hello world.");
    return "Hello " + springApplicationName;
  }
}
