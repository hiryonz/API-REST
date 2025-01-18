package com.Recipe.RecipeApi.model.recipe;



import java.util.List;

import com.Recipe.RecipeApi.model.ingredient.IngredientDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeDTO {
    private Long id;
    private String title;
    private String description;
    private String steps;
    private List<IngredientDTO> ingredient;
}