package com.demo.bbq.business.orderhub.domain.rules;

import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

import java.io.Serializable;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@ToString
public class DiscountRule implements Serializable {

  //input
  private String menuCategory;

  private String menuOptionId;

  //output
  private boolean discountable;

  private double discount;

  public final boolean validateDiscountable(List<String> menuOptionIdList) {
    return isNotEmpty(menuOptionIdList) && menuOptionIdList.contains(menuOptionId);
  }
}
