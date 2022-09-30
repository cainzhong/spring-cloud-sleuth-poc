package com.smax.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.beans.ConstructorProperties;

/**
 * TODO
 *
 * @author zhongtao
 * @since 9/30/2022
 */
@Data
@AllArgsConstructor
public class CustomerAndAddress {
  private Customer customer;

  private Address address;
}
