package com.demo.bbq.business.invoice.domain.repository.database.invoice.entity;

import com.demo.bbq.business.invoice.domain.repository.database.customer.entity.CustomerEntity;
import com.demo.bbq.business.invoice.domain.repository.database.product.entity.ProductEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoices")
public class InvoiceEntity implements Serializable {

  @Id
  @Column(name = "invoice_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "billing_date")
  private String billingDate;

  @OneToOne
  @JoinColumn(name = "customer_id")
  private CustomerEntity customerEntity;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(referencedColumnName = "invoice_id", name = "invoice_id")
  private List<ProductEntity> productList;

  @Column(name = "igv")
  private BigDecimal igv;

  @Enumerated(EnumType.STRING)
  @Column(name = "payment_method")
  private PaymentMethod paymentMethod;

  @Enumerated(EnumType.STRING)
  @Column(name = "payment_status")
  private PaymentStatus paymentStatus;
}
