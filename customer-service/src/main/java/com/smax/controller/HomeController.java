package com.smax.controller;

import com.smax.bean.Address;
import com.smax.bean.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * TODO
 *
 * @author zhongtao
 * @since 9/21/2022
 */
@RestController
@Slf4j
public class HomeController {

  @GetMapping(path = "customers/{id}")
  public ResponseEntity<Customer> getCustomer(@PathVariable("id") long customerId) {
    log.info("Getting customer with id {}", customerId);
    if (customerId < 0 || customerId > NAMES.size() - 1) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    Customer customer = new Customer();
    customer.setId(customerId);
    customer.setName(NAMES.get((int) customerId));

    return new ResponseEntity<>(customer, HttpStatus.OK);
  }

  @GetMapping(path = "addresses/{id}")
  public ResponseEntity<Address> getAddress(@PathVariable("id") long customerId) throws InterruptedException {
    log.info("Getting address for customer with id {}", customerId);
    if (customerId < 0 || customerId > NAMES.size() - 1) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    Address address = new Address();
    address.setId(customerId);
    address.setStreet(STREETS.get((int) customerId));

    return new ResponseEntity<>(address, HttpStatus.OK);
  }

  private static final List<String> STREETS = Arrays.asList(
    "Jefferson Street",
    "Cambridge Road",
    "Olive Street",
    "Old York Road",
    "Aspen Drive",
    "Cherry Lane",
    "10th Street",
    "Pennsylvania Avenue",
    "Central Avenue",
    "8th Street West",
    "Route 11",
    "Street Road",
    "Prospect Street",
    "Devon Road",
    "Forest Avenue",
    "Elm Avenue",
    "Heather Court",
    "Route 202",
    "Willow Drive",
    "Evergreen Lane",
    "Windsor Drive",
    "Mulberry Street",
    "2nd Street North",
    "Sheffield Drive",
    "Ashley Court",
    "Valley Road",
    "Spruce Street",
    "Wood Street",
    "Bridge Street",
    "Monroe Drive",
    "Church Road",
    "Main Street South",
    "Adams Avenue",
    "Delaware Avenue",
    "Somerset Drive",
    "9th Street",
    "3rd Street East",
    "Augusta Drive",
    "Wall Street",
    "Broad Street",
    "Route 2",
    "Fairview Avenue",
    "5th Avenue",
    "Spruce Avenue",
    "Cemetery Road",
    "River Street",
    "Tanglewood Drive",
    "Cedar Street",
    "Canal Street",
    "Market Street");

  private static final List<String> NAMES = Arrays.asList(
    "Daria Domino",
    "Yukiko Yawn",
    "Diane Dalessio",
    "Elijah Elmore",
    "Tyron Thaler",
    "Stefani Stayton",
    "Shara Stam",
    "Earlean Eblin",
    "Jonell Janecek",
    "Thaddeus Tupper",
    "Dorthy Delany",
    "Shanel Saffell",
    "Ricki Rost",
    "Kandy Knecht",
    "Mirella Ma",
    "Rocky Ros",
    "Theo Tosi",
    "Marnie Manzer",
    "Talisha Trottier",
    "Thanh Tamez",
    "Hyacinth Hotard",
    "Dianne Dearman",
    "Marc Medlin",
    "Roxane Rossin",
    "Mellie Marino",
    "Eugene Eng",
    "Charis Curnutt",
    "Lillia Leming",
    "Gretta Gately",
    "Jessica Johnsen",
    "Raven Riera",
    "Roni Rost",
    "Garth Gipson",
    "Demetrice Dell",
    "Eugenie Ead",
    "Jared Julio",
    "Alaina Aguinald ",
    "Hortense Haun",
    "Willia Woodland",
    "Jessenia Jefferis",
    "Jinny June",
    "William Whang",
    "Dessie Down",
    "Katelyn Koren",
    "Barbara Bazaldua",
    "France Frese",
    "Eufemia Evangelista",
    "Neva Nock",
    "Rickey Redd",
    "Norma Nagler");
}
