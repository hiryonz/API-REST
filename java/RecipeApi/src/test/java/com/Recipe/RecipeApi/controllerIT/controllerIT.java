package com.Recipe.RecipeApi.controllerIT;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.Recipe.TestData;
import com.Recipe.RecipeApi.model.recipe.RecipeDTO;
import com.Recipe.RecipeApi.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class controllerIT {
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private RecipeService recipeService;


    @Test
    public void testSaveRecipe() throws Exception {
        final RecipeDTO recipeDTO = TestData.testData1();
        final ObjectMapper mapper = new ObjectMapper();
        final String json = mapper.writeValueAsString(recipeDTO);


        mockMvc.perform(MockMvcRequestBuilders.put("/recipe/save")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(recipeDTO.getTitle()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(recipeDTO.getDescription()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.steps").value(recipeDTO.getSteps()))
        // solo es para ver que se cambia ingerdiente si se cambia su contenido, esta complicado el testearlo no manual debido
        // a que se suele enviar con el id en nulo, por el identity/autoincrement (id = null != id = 1)
        /*.andExpect(MockMvcResultMatchers.jsonPath("$.ingredient").value(recipeDTO.getIngredient()))*/;

    }

    @Test
    public void testUpdateRecipe() throws Exception {
        RecipeDTO recipeDTO = TestData.testData1();
        recipeService.save(recipeDTO);
        
        recipeDTO = TestData.testData2();
        recipeDTO.setId((long) 1);    
        recipeDTO.setTitle("actualizado");


        final ObjectMapper mapper = new ObjectMapper();
        final String json = mapper.writeValueAsString(recipeDTO);



        mockMvc.perform(MockMvcRequestBuilders.put("/recipe/save")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("actualizado"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(recipeDTO.getDescription()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.steps").value(recipeDTO.getSteps()))
        // solo es para ver que se cambia ingerdiente si se cambia su contenido, esta complicado el testearlo no manual debido
        // a que se suele enviar con el id en nulo, por el identity/autoincrement (id = null != id = 1)
        /*.andExpect(MockMvcResultMatchers.jsonPath("$.ingredient").value(mapper.writeValueAsString(recipeDTO.getIngredient())))*/;
    }


    @Test
    public void testFindByIdReturnsEmptyWhenNoRecipeIsFound_HTTP404() throws Exception {
        final String id = "1";

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/" + id))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testFindByIdReturnsRecipeWhenRecipeIsFound_HTTP200() throws Exception {
        final RecipeDTO recipeDTO = TestData.testData1();
        recipeService.save(recipeDTO);
    
        final String id = "1";



        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/" + id))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(recipeDTO.getTitle()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(recipeDTO.getDescription()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.steps").value(recipeDTO.getSteps()))
        // solo es para ver que se cambia ingerdiente si se cambia su contenido, esta complicado el testearlo no manual debido
        // a que se suele enviar con el id en nulo, por el identity/autoincrement (id = null != id = 1)
        /*.andExpect(MockMvcResultMatchers.jsonPath("$.ingredient").value(recipeDTO.getIngredient()))*/;
    }


    @Test
    public void testThatFindAllReturnsEmptyWhenNoRecpeIsFound_HTTP204() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe"))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatFindAllReturnsRecipeWhenRecpeIsFound_HTTP200() throws Exception {
        final RecipeDTO recipeDTO = TestData.testData1();
        recipeService.save(recipeDTO);
        recipeService.save(recipeDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatDeleteByIdReturnsHttp404WhenNoRecipeIsFound() throws Exception {
        final String id = "1";
        mockMvc.perform(MockMvcRequestBuilders.delete("/recipe/" + id))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    
    @Test
    public void testThatDeleteByIdReturnsHttp200WhenRecipeIsFound() throws Exception {
        final RecipeDTO recipeDTO = TestData.testData1();

        recipeService.save(recipeDTO);
        final String id = "1";
        mockMvc.perform(MockMvcRequestBuilders.delete("/recipe/" + id))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
