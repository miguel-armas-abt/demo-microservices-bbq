package com.demo.bbq.infrastructure.apigateway.infrastructure.config.restclient;

import java.time.Duration;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Timeout {
  private Duration connection = Duration.ofSeconds(3);
  private Duration response = Duration.ofSeconds(5);
  private Duration request = Duration.ofSeconds(3);
}
