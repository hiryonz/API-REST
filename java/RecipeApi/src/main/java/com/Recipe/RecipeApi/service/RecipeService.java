package com.Recipe.RecipeApi.service;

import java.util.List;
import java.util.Optional;

import com.Recipe.RecipeApi.model.recipe.RecipeDTO;

public interface RecipeService {

    RecipeDTO save(RecipeDTO Recipe);

    boolean deleteById(String id);

    Optional<RecipeDTO> findById(String id);

    List<RecipeDTO> findAll();

    boolean isRecipeExist(String id);
}
