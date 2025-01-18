package com.Recipe.RecipeApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Recipe.RecipeApi.model.recipe.RecipeEntity;

@Repository
public interface RecipeRepo extends JpaRepository<RecipeEntity, String> {
    
}
