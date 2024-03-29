package com.demo.bbq.business.menu.infrastructure.rest;

import com.demo.bbq.business.menu.application.dto.response.MenuOptionResponse;
import com.demo.bbq.business.menu.infrastructure.doc.DocumentationConfig.DocumentationExample;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import com.demo.bbq.business.menu.application.dto.request.MenuOptionSaveRequest;
import com.demo.bbq.business.menu.application.dto.request.MenuOptionUpdateRequest;
import com.demo.bbq.business.menu.infrastructure.doc.DocumentationConfig;
import com.demo.bbq.support.exception.documentation.ApiExceptionJsonExample;
import com.demo.bbq.support.exception.model.dto.ApiExceptionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

@ApiResponses(value = {
    @ApiResponse(responseCode = "400",
        content = @Content(schema = @Schema(implementation = ApiExceptionDto.class), examples = @ExampleObject(value = ApiExceptionJsonExample.BAD_REQUEST))),
    @ApiResponse(responseCode = "404",
        content = @Content(schema = @Schema(implementation = ApiExceptionDto.class), examples = @ExampleObject(value = ApiExceptionJsonExample.NOT_FOUND))),
})
public interface MenuOptionRestService {

  @Operation(summary = "Get a menu by ID", tags = DocumentationConfig.TAG)
  @ApiResponse(responseCode = "200")
  ResponseEntity<MenuOptionResponse> findByProductCode(HttpServletRequest servletRequest,
                                                       @Parameter(example = DocumentationExample.PRODUCT_CODE) String productCode);

  @Operation(summary = "Get a menu list by category", tags = DocumentationConfig.TAG)
  @ApiResponse(responseCode = "200")
  ResponseEntity<List<MenuOptionResponse>> findByCategory(HttpServletRequest servletRequest,
                                                          @Parameter(description = "Menu category", examples = {
                                                      @ExampleObject(value = DocumentationExample.CATEGORY_MAIN_DISH),
                                                      @ExampleObject(value = DocumentationExample.CATEGORY_DRINK),
                                                      @ExampleObject(value = DocumentationExample.CATEGORY_DESSERT),
                                                  }) String categoryCode);

  @Operation(summary = "Save a menu", tags = DocumentationConfig.TAG)
  @ApiResponse(responseCode = "201")
  ResponseEntity<Void> save(HttpServletRequest servletRequest,
                            @RequestBody(content = {@Content(schema = @Schema(implementation = MenuOptionSaveRequest.class))}) MenuOptionSaveRequest menuOption);

  @Operation(summary = "Update a menu", tags = DocumentationConfig.TAG)
  @ApiResponse(responseCode = "201")
  ResponseEntity<Void> update(HttpServletRequest servletRequest,
                              @RequestBody(content = {@Content(schema = @Schema(implementation = MenuOptionUpdateRequest.class))}) MenuOptionUpdateRequest menuOption,
                              @Parameter(example = DocumentationExample.PRODUCT_CODE) String productCode);

  @Operation(summary = "Delete a menu", tags = DocumentationConfig.TAG)
  @ApiResponse(responseCode = "204")
  ResponseEntity<Void> delete(HttpServletRequest servletRequest,
                              @Parameter(example = DocumentationExample.PRODUCT_CODE) String productCode);
}
