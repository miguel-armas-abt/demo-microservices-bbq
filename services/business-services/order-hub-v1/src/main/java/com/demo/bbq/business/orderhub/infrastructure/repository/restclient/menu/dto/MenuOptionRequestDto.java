package com.demo.bbq.business.orderhub.infrastructure.repository.restclient.menu.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuOptionRequestDto implements Serializable {

  private String description;
  private String category;
  private BigDecimal price;
  private boolean active;
}