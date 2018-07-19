package com.amaris.ai.cloud.web.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

  @RequestMapping("/greeting")
  public Greeting greeting(@RequestParam(value = "location") String devt_location, @RequestParam(value = "type") String usr_mctype) {
    return new Greeting(devt_location, usr_mctype);
  }
}
