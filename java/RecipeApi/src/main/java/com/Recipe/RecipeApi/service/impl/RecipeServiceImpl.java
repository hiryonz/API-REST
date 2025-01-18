package com.Recipe.RecipeApi.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Recipe.RecipeApi.model.ingredient.IngredientDTO;
import com.Recipe.RecipeApi.model.ingredient.IngredientEntity;
import com.Recipe.RecipeApi.model.recipe.RecipeDTO;
import com.Recipe.RecipeApi.model.recipe.RecipeEntity;
import com.Recipe.RecipeApi.repository.RecipeRepo;
import com.Recipe.RecipeApi.service.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepo recipeRepo;


    //tranform from Entity to DTO and vice versa
    private RecipeEntity convertToEntity(RecipeDTO recipeDto) { 
        RecipeEntity recipewEntity = new RecipeEntity();

        recipewEntity.setId(recipeDto.getId());
        recipewEntity.setTitle(recipeDto.getTitle());
        recipewEntity.setDescription(recipeDto.getDescription());
        recipewEntity.setSteps(recipeDto.getSteps());

        List<IngredientEntity> ingredientEntityTransformed = recipeDto.getIngredient().stream()
        .map(ingredient -> {
            IngredientEntity ingredientEntity = new IngredientEntity();
            ingredientEntity.setIngredient(ingredient.getIngredient());
            ingredientEntity.setQuantity(ingredient.getQuantity());
            ingredientEntity.setRecipe(recipewEntity);

            return ingredientEntity;
        }).collect(Collectors.toList());

        recipewEntity.setIngredient(ingredientEntityTransformed);

        return recipewEntity;
    }

    private RecipeDTO convertTDto(RecipeEntity recipeEntity) {
        List<IngredientDTO> ingredientDTO = recipeEntity.getIngredient().stream()
        .map(ingredient -> 
            IngredientDTO.builder()
            .id(ingredient.getId())
            .ingredient(ingredient.getIngredient())
            .quantity(ingredient.getQuantity())
            .build())
        .collect(Collectors.toList());

        return RecipeDTO.builder()
        .id(recipeEntity.getId())
        .title(recipeEntity.getTitle())
        .description(recipeEntity.getDescription())
        .steps(recipeEntity.getSteps())
        .ingredient(ingredientDTO)
        .build();
    }


    @Override
    public RecipeDTO save(RecipeDTO recipe) {
        final RecipeEntity recipeEntity = convertToEntity(recipe);

        final RecipeEntity savedRecipe = recipeRepo.save(recipeEntity);

        return convertTDto(savedRecipe);
    }

    @Override
    public boolean deleteById(String id) {
        final Optional<RecipeEntity> foundRecipe = recipeRepo.findById(id);

        if(foundRecipe.isPresent()) {
            recipeRepo.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Optional<RecipeDTO> findById(String id) {
        final Optional<RecipeEntity> foundRecipe = recipeRepo.findById(id);
        final Optional<RecipeDTO> foundRecipeDTO = foundRecipe.map(recipe -> convertTDto(recipe));

        return foundRecipeDTO;
    }

    @Override
    public List<RecipeDTO> findAll() {
        final List<RecipeEntity> foundRecipes = recipeRepo.findAll();
        final List<RecipeDTO> foundRecipesDTO = foundRecipes
        .stream()
        .map(recipe -> convertTDto(recipe))
        .collect(Collectors.toList());

        return foundRecipesDTO;
    }

    @Override
    public boolean isRecipeExist(String id) {
        return recipeRepo.existsById(id);
    }
}
