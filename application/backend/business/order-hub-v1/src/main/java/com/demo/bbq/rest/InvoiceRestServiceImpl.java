package com.demo.bbq.rest;

import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.demo.bbq.rest.handler.InvoiceHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class InvoiceRestServiceImpl {

  private static final String BASE_URI = "/bbq/bff/order-hub/v1/invoices/";

  @Bean("invoices")
  public RouterFunction<ServerResponse> build(InvoiceHandler invoiceHandler) {
    return nest(
        path(BASE_URI),
        route()
            .POST("proformas", accept(APPLICATION_STREAM_JSON) , invoiceHandler::generateProforma)
            .POST("send-to-pay", accept(APPLICATION_STREAM_JSON), invoiceHandler::sendToPay)
            .build()
    );
  }
}