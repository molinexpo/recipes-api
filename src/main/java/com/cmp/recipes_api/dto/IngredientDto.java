package com.cmp.recipes_api.dto;

import com.cmp.recipes_api.enums.IngredientType;
import com.cmp.recipes_api.enums.QuantityUnit;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientDto {
    private Integer id;
    private String name;
    private Long quantity;
    private QuantityUnit unit;
    private IngredientType type;
}
