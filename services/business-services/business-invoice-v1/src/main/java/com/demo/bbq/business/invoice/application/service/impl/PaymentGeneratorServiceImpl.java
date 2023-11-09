package com.demo.bbq.business.invoice.application.service.impl;

import com.demo.bbq.business.invoice.application.service.PaymentGeneratorService;
import com.demo.bbq.business.invoice.application.service.ProformaInvoiceService;
import com.demo.bbq.business.invoice.domain.exception.InvoiceException;
import com.demo.bbq.business.invoice.domain.model.request.PaymentRequest;
import com.demo.bbq.business.invoice.domain.model.response.ProformaInvoice;
import com.demo.bbq.business.invoice.infrastructure.broker.producer.InvoiceProducer;
import com.demo.bbq.business.invoice.infrastructure.mapper.InvoiceMapper;
import com.demo.bbq.business.invoice.infrastructure.repository.database.InvoiceRepositoryHelper;
import com.google.gson.Gson;
import io.reactivex.Completable;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentGeneratorServiceImpl implements PaymentGeneratorService {
  private final ProformaInvoiceService proformaInvoiceService;
  private final InvoiceRepositoryHelper repositoryHelper;
  private final InvoiceProducer invoiceProducer;
  private final InvoiceMapper invoiceMapper;

  @Override
  public Completable sendToPay(PaymentRequest paymentRequest) {
    AtomicReference<BigDecimal> totalAmount = new AtomicReference<>();

    return proformaInvoiceService.generateProformaInvoice(paymentRequest.getProductList())
        .doOnSuccess(validateProforma::accept)
        .doOnSuccess(proforma -> totalAmount.set(proforma.getTotal()))
        .map(invoice -> invoiceMapper.toEntity(invoice, paymentRequest.getCustomer(), paymentRequest.getPaymentMethod()))
        .map(repositoryHelper::buildEntity)
        .map(invoice -> invoiceMapper.invoiceToPayment(invoice, totalAmount.get()))
        .doOnSuccess(payment-> invoiceProducer.sendMessage(new Gson().toJson(payment)))
        .ignoreElement();
  }

  private static final Consumer<ProformaInvoice> validateProforma = proforma -> {
    if(proforma.getTotal().compareTo(BigDecimal.ZERO) == 0) {
      throw InvoiceException.ERROR0000.buildException();
    }
  };

}
