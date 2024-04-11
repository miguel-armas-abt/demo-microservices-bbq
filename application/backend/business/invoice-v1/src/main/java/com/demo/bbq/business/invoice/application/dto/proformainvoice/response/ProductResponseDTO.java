package com.demo.bbq.business.invoice.application.dto.proformainvoice.response;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO implements Serializable {

  private String description;
  private Integer quantity;
  private BigDecimal unitPrice;
  private BigDecimal subtotal;

}