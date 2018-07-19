package com.amaris.ai.cloud.web.server;

public class Greeting {

  private final String location;
  private final String type;

  public Greeting(String location, String type) {
    this.location = location;
    this.type = type;
  }

  public String getLocation() {
    return location;
  }

  public String getType() {
    return type;
  }

}
