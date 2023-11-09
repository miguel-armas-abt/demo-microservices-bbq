package com.demo.bbq.business.invoice.application.service.impl;

import com.demo.bbq.business.invoice.domain.model.request.ProductRequest;
import com.demo.bbq.business.invoice.domain.model.response.Product;
import com.demo.bbq.business.invoice.domain.model.response.ProformaInvoice;
import com.demo.bbq.business.invoice.infrastructure.mapper.InvoiceMapper;
import com.demo.bbq.business.invoice.infrastructure.properties.InvoiceProperties;
import com.demo.bbq.business.invoice.application.service.ProformaInvoiceService;
import io.reactivex.Single;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProformaInvoiceServiceImpl implements ProformaInvoiceService {

  private final InvoiceProperties properties;

  private final InvoiceMapper proformaInvoiceMapper;

  @Override
  public Single<ProformaInvoice> generateProformaInvoice(List<ProductRequest> products) {
    AtomicReference<BigDecimal> subtotalInvoice = new AtomicReference<>(BigDecimal.ZERO);
    List<Product> productList = products.stream()
        .map(proformaInvoiceMapper::toProduct)
        .peek(product -> subtotalInvoice.set(subtotalInvoice.get().add(product.getSubtotal()))).collect(Collectors.toList());
    return Single.just(toProformaInvoice(productList, subtotalInvoice.get()));
  }

  private ProformaInvoice toProformaInvoice(List<Product> productList, BigDecimal subtotal) {
    BigDecimal igv = properties.getIgv();
    return ProformaInvoice.builder()
        .igv(igv)
        .subtotal(subtotal)
        .productList(productList)
        .total(subtotal.add(subtotal.multiply(igv)))
        .build();
  }
}
