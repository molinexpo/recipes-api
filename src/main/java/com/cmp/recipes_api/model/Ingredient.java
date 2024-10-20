package com.cmp.recipes_api.model;

import com.cmp.recipes_api.enums.IngredientType;
import com.cmp.recipes_api.enums.QuantityUnit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private Long quantity;

    @Column
    @Enumerated(EnumType.STRING)
    private QuantityUnit unit;

    @Column
    @Enumerated(EnumType.STRING)
    private IngredientType type;

}
