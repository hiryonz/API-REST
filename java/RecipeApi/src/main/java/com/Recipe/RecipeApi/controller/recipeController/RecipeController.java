package com.Recipe.RecipeApi.controller.recipeController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Recipe.RecipeApi.model.recipe.RecipeDTO;
import com.Recipe.RecipeApi.service.RecipeService;

@RestController
public class RecipeController {
    
    @Autowired
    private RecipeService recipeService;
   
    @PutMapping(path = "/recipe/save")
    public ResponseEntity<RecipeDTO> createUpdateRecipe(@RequestBody final RecipeDTO recipeDTO) {

        boolean updated = false;
        if(recipeDTO.getId() != null) {
            final Optional<RecipeDTO> isRecipeFound = recipeService.findById(recipeDTO.getId().toString());
            if(!isRecipeFound.isEmpty()) {
                updated = true;
            }
        }
        final RecipeDTO recipe = recipeService.save(recipeDTO);

        if(updated) {
            return new ResponseEntity<RecipeDTO>(recipe,HttpStatus.OK);
        }

        return new ResponseEntity<RecipeDTO>(recipe, HttpStatus.CREATED);
    }

    @GetMapping(path = "/recipe/{id}")
    public ResponseEntity<RecipeDTO> getRecipeById(@PathVariable final String id) {

            final Optional<RecipeDTO> recipe = recipeService.findById(id);

            if(recipe.isEmpty()) {
                return new ResponseEntity<RecipeDTO>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<RecipeDTO>(recipe.get(), HttpStatus.OK);
    }

    @GetMapping(path = "/recipe")
    public ResponseEntity<List<RecipeDTO>> getRecipe() {
            final List<RecipeDTO> recipe = recipeService.findAll();
            if(recipe.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @DeleteMapping(path = "/recipe/{id}")
    public ResponseEntity<RecipeDTO> deleteRecipe(@PathVariable final String id) {
            final boolean isRecipeDeleted = recipeService.deleteById(id);
            if(isRecipeDeleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }

}
