package com.demo.bbq.business.menu.application.dto.request;

import com.demo.bbq.business.menu.infrastructure.doc.DocumentationConfig.DocumentationExample;
import com.demo.bbq.business.menu.application.constant.MenuRegex;
import com.demo.bbq.support.constant.RegexConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuOptionUpdateRequest implements Serializable {

  @Schema(example = DocumentationExample.DESCRIPTION)
  @Pattern(regexp = RegexConstant.ANY_STRING, message = "Name has invalid format")
  @Size(min = 3, max = 300)
  @NotNull(message = "description cannot be null")
  private String description;

  @Schema(example = DocumentationExample.CATEGORY_MAIN_DISH)
  @Pattern(regexp = MenuRegex.CATEGORY, message = "Invalid menu option category")
  @NotNull(message = "category cannot be null")
  private String category;

  @Schema(example = DocumentationExample.UNIT_PRICE)
  @NotNull(message = "unitPrice cannot be null")
  private BigDecimal unitPrice;
}