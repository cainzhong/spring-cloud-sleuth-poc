package com.smax.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author zhongtao
 * @since 9/21/2022
 */
@Service
@Slf4j
public class HomeService {

  @Value("${spring.application.name}")
  public String springApplicationName;
  public String printHello(){
    return "Hello " + springApplicationName;
  }
}
