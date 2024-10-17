package com.cmp.recipes_api.dto;

import com.cmp.recipes_api.enums.Difficulty;
import com.cmp.recipes_api.enums.RecipeType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RecipeDto {
    private Integer id;
    private String name;
    private RecipeType type;
    private Difficulty difficulty;
    private Integer numberOfPeople;
    private Integer duration;
    private Date creationDate;
}
