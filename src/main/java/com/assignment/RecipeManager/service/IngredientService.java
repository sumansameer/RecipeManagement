package com.assignment.RecipeManager.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.RecipeManager.model.Ingredient;
import com.assignment.RecipeManager.repository.IngredientRepository;

import java.util.List;

@Service
public class IngredientService {
	@Autowired
    private IngredientRepository ingredientRepository;

    public Ingredient addIngredient(Ingredient ingredient) {
    	try {
    		return ingredientRepository.save(ingredient);
    	} catch(Exception e) {
    		throw new RuntimeException("Failed to add ingredient");
    	}
    }

    public List<Ingredient> getAllIngredients() {
    	try {
    		return ingredientRepository.findAll();
    	} catch(Exception e) {
    		throw new RuntimeException("Failed to retrieve ingredients");
    	}
    }

    public Ingredient getIngredientById(Long id) {
    	try {
    		return ingredientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ingredient not found with id: " + id));
    	} catch (Exception e) {
    		throw new RuntimeException("Failed to retrieve ingredient with id: " + id);
    	}
    }
    
    public Ingredient updateIngredient(Long id, Ingredient updatedIngredient) {
    	try {
	    	Ingredient existingIngredient = ingredientRepository.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Ingredient not found with id: " + id));
	
	        // Update the existing recipe with the new values
	        existingIngredient.setName(updatedIngredient.getName());
	        
	        // Save the updated recipe
	        return ingredientRepository.save(existingIngredient);
    	} catch(Exception e) {
    		throw new RuntimeException("Failed to update ingredient with id: " + id);
    	}
    }

    public void deleteIngredient(Long id) {
    	try {
    		ingredientRepository.deleteById(id);
    	} catch(Exception e) {
    		throw new RuntimeException("Failed to delete ingredient with id: " + id);
    	}
    }

 
    public List<Ingredient> getIngredientsByNameContaining(String partialName) {
    	try {
    		return ingredientRepository.findByNameContainingIgnoreCase(partialName);
    	} catch (Exception e) {
    		throw new RuntimeException("Failed to retrieve ingredient that contains " + partialName);
    	}
    }
}
