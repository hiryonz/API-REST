package com.Recipe;

import java.util.ArrayList;
import java.util.List;

import com.Recipe.RecipeApi.model.ingredient.IngredientDTO;
import com.Recipe.RecipeApi.model.recipe.RecipeDTO;

public class TestData {
    


    private static List<IngredientDTO> ingredientDTO() {


        final List<IngredientDTO> ingredient = new ArrayList<>();

        
        final IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setIngredient("ingre1");
        ingredientDTO.setQuantity(1);


        final IngredientDTO ingredientDTO2 = new IngredientDTO();
        ingredientDTO2.setIngredient("ingre2");
        ingredientDTO2.setQuantity(2);

        ingredient.add(ingredientDTO);
        ingredient.add(ingredientDTO2);

        return ingredient;
    }

    public static RecipeDTO testData1() {
        return RecipeDTO.builder()
        .title("title 1")
        .description("descrip[tion1]")
        .steps("step1")
        .ingredient(ingredientDTO())
        .build();
    }


    private static List<IngredientDTO> ingredientDTO2() {


        final List<IngredientDTO> ingredient = new ArrayList<>();

        
        final IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setIngredient("ingre1actualozapd");
        ingredientDTO.setQuantity(3);


        final IngredientDTO ingredientDTO2 = new IngredientDTO();
        ingredientDTO2.setIngredient("ingre2actualizado");
        ingredientDTO2.setQuantity(10);

        ingredient.add(ingredientDTO);
        ingredient.add(ingredientDTO2);

        return ingredient;
    }

    public static RecipeDTO testData2() {
        return RecipeDTO.builder()
        .title("title 1")
        .description("descrip[tion1]")
        .steps("step1")
        .ingredient(ingredientDTO2())
        .build();
    }



}
