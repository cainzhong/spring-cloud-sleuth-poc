package com.smax.controller;

import brave.baggage.BaggageField;
import com.smax.bean.Address;
import com.smax.bean.Customer;
import com.smax.bean.CustomerAndAddress;
import com.smax.service.AddressClient;
import com.smax.service.CustomerClient;
import com.smax.service.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * TODO
 *
 * @author zhongtao
 * @since 9/21/2022
 */
@RestController
@Slf4j
public class HomeController {

  private CustomerClient customerClient;

  private AddressClient addressClient;
  private Tracer tracer;
  private BaggageField userIdField;

  private HomeService homeService;

  @Autowired
  public HomeController(CustomerClient customerClient, AddressClient addressClient, Tracer tracer, BaggageField userIdField, HomeService homeService) {
    this.customerClient = customerClient;
    this.addressClient = addressClient;
    this.tracer = tracer;
    this.userIdField = userIdField;
    this.homeService = homeService;
  }

  @RequestMapping("/")
  public String home(@RequestHeader Map<String, String> headers) {
    log.info("Calling Application {}.", homeService.springApplicationName);
    log.info("trace id: {}", tracer.currentSpan().context().traceId());

    headers.forEach((key, value) -> {
      log.info(String.format("Header '%s' = %s", key, value));
    });

    return homeService.printHello();
  }

  @GetMapping(path = "customers/{id}")
  public CustomerAndAddress getCustomerWithAddress(@PathVariable("id") long customerId) {
    String traceId = tracer.currentSpan().context().traceId();
    log.info("Collecting customer and address with id {} from customer service. Trace id: {}", customerId, traceId);

    // The field user-id will be added into http header.
    userIdField.updateValue(String.valueOf(customerId));

    Customer customer = customerClient.getCustomer(customerId);
    Address address = addressClient.getAddressForCustomerId(customerId);
    return new CustomerAndAddress(customer, address);
  }
}
