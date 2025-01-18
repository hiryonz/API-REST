package com.Recipe.RecipeApi.model.ingredient;

import com.Recipe.RecipeApi.model.recipe.RecipeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table (name = "ingredient")
public class IngredientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ingredient;
    
    @Column(nullable = false)
    private float quantity;

    //permite la creacion de foreign keys (ManyToOne, OneToOne, ManyToMany)
    @ManyToOne /*(cascade = CascadeType.REMOVE)*/
    @JoinColumn(name = "recipe_id", nullable = false)
    private RecipeEntity recipe;
}
