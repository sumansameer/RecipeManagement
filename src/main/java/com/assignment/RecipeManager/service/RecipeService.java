package com.assignment.RecipeManager.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.RecipeManager.model.Recipe;
import com.assignment.RecipeManager.repository.RecipeRepository;

import java.util.List;

@Service
public class RecipeService {
	 	@Autowired
	    private RecipeRepository recipeRepository;

	    public Recipe addRecipe(Recipe recipe) {
	    	try {
	    		return recipeRepository.save(recipe);
	    	} catch(Exception e) {
	    		throw new RuntimeException("Failed to add recipe");
	    	}
 	    }

	    public List<Recipe> getAllRecipes() {
	    	try {
	    		return recipeRepository.findAll();
	    	} catch(Exception e) {
	    		throw new RuntimeException("Failed to retrieve all recipes");
	    	}
	    }
	    
	    public Recipe updateRecipe(Long id, Recipe updatedRecipe) {
	    	try {
		    	Recipe existingRecipe = recipeRepository.findById(id)
		                .orElseThrow(() -> new IllegalArgumentException("Recipe not found with id: " + id));
	
		        // Update the existing recipe with the new values
		        existingRecipe.setName(updatedRecipe.getName());
		        existingRecipe.setInstructions(updatedRecipe.getInstructions());
		        existingRecipe.setServings(updatedRecipe.getServings());
		        existingRecipe.setVegetarian(updatedRecipe.isVegetarian());
	
		        // Save the updated recipe
		        return recipeRepository.save(existingRecipe);
	    	} catch(Exception e) {
	    		throw new RuntimeException("Failed to update recipe with id: " + id);
	    	}
	    }
	    
	    public void deleteRecipe(Long id) {
	    	try {
	    		recipeRepository.deleteById(id);
	    	} catch(Exception e) {
	    		throw new RuntimeException("Failed to delete recipe with id: " + id);
	    	}
	    }

	    public Recipe getRecipeById(Long id) {
	    	try {
	    		return recipeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Recipe not found with id: " + id));
	    	} catch(Exception e) {
	    		throw new RuntimeException("Failed to get recipe for id: " + id);
	    	}
	    }
	    
	    public List<Recipe> getVegetarianRecipes() {
	    	try {
	    		return recipeRepository.findByVegetarian(true);
	    	} catch(Exception e) {
	    		throw new RuntimeException("Failed to retrieve all vegetarian recipes");
	    	}
	    }

	    public List<Recipe> getRecipesByServings(int servings) {
	    	try {
	    		return recipeRepository.findByServings(servings);
	    	} catch(Exception e) {
	    		throw new RuntimeException("Failed to retrieve recipes with " + servings + " servings");
	    	}
	    }

	    public List<Recipe> getRecipesWithIngredient(String ingredientName) {
	    	try {
	    		return recipeRepository.findByRecipeIngredientsIngredientName(ingredientName);
	    	} catch(Exception e) {
	    		throw new RuntimeException("Failed to retrieve recipes with ingredient " + ingredientName);
	    	}
	    }

	    public List<Recipe> getRecipesWithoutIngredientAndWithInstructionsContaining(String excludedIngredient, String instructionText) {
	    	try {
	    		return recipeRepository.findByIngredientsNameNotAndInstructionsContaining(excludedIngredient, instructionText);
	    	} catch(Exception e) { 
	    		throw new RuntimeException("Failed to retrieve recipes without " + excludedIngredient + " and instructions containing " + instructionText);
	    	}
	    }
	    
	    public List<Recipe> getServingsAndIngredient(int servings, String ingredient) {
	    	try {
	    		return recipeRepository.findByServingsAndIngredient(servings, ingredient);
	    	} catch(Exception e) {
	    		throw new RuntimeException("Failed to retrieve recipes with " + servings + " servings and with Ingredient " + ingredient);
	    	}
	    }
}
