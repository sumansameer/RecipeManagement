package com.assignment.RecipeManager.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.assignment.RecipeManager.model.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>{
//	custom queries
	
//	get all
	List<Recipe> findAll();

//	add a recipe
	@SuppressWarnings("unchecked")
	Recipe save(Recipe recipe);
	
//	get recipes by vegetarian status
	List<Recipe> findByVegetarian(boolean vegetarian);
	
//	get recipes by number of servings
	List<Recipe> findByServings(int servings);

//	find recipes with ingredient name	
	@Query("SELECT DISTINCT r FROM Recipe r INNER JOIN r.recipeIngredients ri WHERE ri.ingredient.name = ?1")
	List<Recipe> findByRecipeIngredientsIngredientName(String ingredientName);
	
//	find recipes without ingredient and contains instruction text
	@Query("SELECT DISTINCT r FROM Recipe r " +
	           "INNER JOIN r.recipeIngredients ri " +
	           "WHERE ri.ingredient.name != ?1 " +
	           "AND lower(r.instructions) LIKE %?2%")
    List<Recipe> findByIngredientsNameNotAndInstructionsContaining(String excludedIngredient, String instructionText);
	
//	find recipes with number of servings and with ingredient
	 @Query("SELECT DISTINCT r FROM Recipe r " +
	            "INNER JOIN r.recipeIngredients ri " +
	            "WHERE r.servings = ?1 AND ri.ingredient.name = ?2")
    List<Recipe> findByServingsAndIngredient(int servings, String ingredient);
}




