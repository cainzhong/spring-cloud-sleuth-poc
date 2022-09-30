package com.smax.service;

import com.smax.bean.Address;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * TODO
 *
 * @author zhongtao
 * @since 9/30/2022
 */
@Service
public class AddressClient {
  private final RestTemplate restTemplate;
  private final String baseUrl;

  public AddressClient(
    RestTemplate restTemplate, @Value("${addressClient.baseUrl}") String baseUrl) {
    this.restTemplate = restTemplate;
    this.baseUrl = baseUrl;
  }

  public Address getAddressForCustomerId(long id) {
    return restTemplate.getForObject(String.format("%s/addresses/%d", baseUrl, id), Address.class);
  }
}
