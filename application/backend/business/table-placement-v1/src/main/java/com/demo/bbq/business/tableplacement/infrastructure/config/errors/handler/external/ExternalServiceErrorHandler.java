package com.demo.bbq.business.tableplacement.infrastructure.config.errors.handler.external;

import com.demo.bbq.business.tableplacement.application.properties.ServiceConfigurationProperties;
import com.demo.bbq.utils.errors.handler.external.ExternalClientErrorUtil;
import com.demo.bbq.utils.errors.handler.external.service.RestClientErrorService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ExternalServiceErrorHandler {

  private final List<RestClientErrorService> services;
  private final ServiceConfigurationProperties properties;

  public Mono<? extends Throwable> handleError(ClientResponse clientResponse,
                                               Class<?> errorWrapperClass,
                                               String serviceName) {
    return ExternalClientErrorUtil.handleError(clientResponse, errorWrapperClass, serviceName, services, properties);
  }
}
