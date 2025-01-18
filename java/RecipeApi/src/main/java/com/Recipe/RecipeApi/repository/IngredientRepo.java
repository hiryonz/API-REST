package com.Recipe.RecipeApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Recipe.RecipeApi.model.ingredient.IngredientEntity;

@Repository
public interface IngredientRepo extends JpaRepository<IngredientEntity, String> {
    
}
