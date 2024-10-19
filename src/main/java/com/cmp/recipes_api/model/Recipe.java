package com.cmp.recipes_api.model;

import com.cmp.recipes_api.enums.Difficulty;
import com.cmp.recipes_api.enums.RecipeType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private RecipeType type;

    @Column
    private Difficulty difficulty;

    @Column
    private Integer numberOfPeople;

    @Column
    private Integer duration;

    @Column
    private Date creationDate;

    @Transient
    private List<String> ingredients = List.of("lemon", "sugar", "water");

}
