package com.emapaez.storepay.features.productCategory.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProductCategoryRequest (@Schema(description = "The product category name, max length: 30", example = "Drinks", maxLength = 30, required = true)
                                      @NotBlank(message = "The name cannot be null or blank.")
                                      @Size(max = 30, message = "The name must be a maximum of 30 characters.")
                                      String name,
                                      @Schema(description = "Product category description, max length: 200", example = "beverages for nutritional hydration.", maxLength = 200, required = true)
                                      @NotBlank(message = "The description cannot be null or blank.")
                                      @Size(max = 200, message = "The description must be a maximum of 200 characters.")
                                      String description){
}
