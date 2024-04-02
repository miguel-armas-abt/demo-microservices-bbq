package com.demo.bbq.business.invoice.infrastructure.rest;

import com.demo.bbq.business.invoice.application.dto.proformainvoice.response.ProformaInvoiceResponse;
import com.demo.bbq.business.invoice.application.service.InvoicePaymentService;
import com.demo.bbq.business.invoice.application.service.ProformaInvoiceService;
import com.demo.bbq.business.invoice.application.dto.invoicepayment.request.PaymentRequest;
import com.demo.bbq.business.invoice.application.dto.proformainvoice.request.ProductRequest;
import com.demo.bbq.support.logstash.Markers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import java.util.List;
import java.util.function.Consumer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/bbq/business/invoice/v1")
public class InvoiceRestServiceImpl {

  private final ProformaInvoiceService proformaInvoiceService;
  private final InvoicePaymentService invoicePaymentService;

  @PostMapping(value = "/proformas", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
  public Single<ProformaInvoiceResponse> generateProforma(HttpServletRequest servletRequest,
                                                          @Valid @RequestBody List<ProductRequest> productList) {
    logRequest.accept(servletRequest);
    return proformaInvoiceService.generateProformaInvoice(productList);
  }

  @PostMapping("/send-to-pay")
  public Completable sendToPay(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
                               @Valid @RequestBody PaymentRequest paymentRequest) {
    logRequest.accept(servletRequest);
    return invoicePaymentService.sendToPay(paymentRequest)
        .doOnComplete(() -> servletResponse.setStatus(201));
  }

  private final static Consumer<HttpServletRequest> logRequest = servletRequest->
      log.info(Markers.SENSITIVE_JSON, "{}", servletRequest.getMethod() + ": " + servletRequest.getRequestURI());

}
