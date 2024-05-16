package com.assignment.RecipeManager.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.assignment.RecipeManager.model.Ingredient;
import com.assignment.RecipeManager.model.IngredientDTO;
import com.assignment.RecipeManager.service.IngredientService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
	
	@Autowired
    private IngredientService ingredientService;

    @PostMapping
    public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingredient) {
    	try {
    		Ingredient addedIngredient = ingredientService.addIngredient(ingredient);
    		return ResponseEntity.status(HttpStatus.CREATED).body(addedIngredient);
    	} catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }

    @GetMapping
    public ResponseEntity<List<IngredientDTO>> getAllIngredients() {
    	try {
	        List<Ingredient> ingredients = ingredientService.getAllIngredients();
	        List<IngredientDTO> ingredientDTOs = ingredients.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	        return ResponseEntity.ok(ingredientDTOs);
    	} catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}    
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientDTO> getIngredientById(@PathVariable("id") Long id) {
    	try {
	        Ingredient ingredient = ingredientService.getIngredientById(id);
	        if (ingredient != null) {
	        	IngredientDTO ingredientDTO = convertToDTO(ingredient);
	            return ResponseEntity.ok(ingredientDTO);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
    	} catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable Long id, @RequestBody Ingredient updatedIngredient) {
    	try {
	    	Ingredient updated = ingredientService.updateIngredient(id, updatedIngredient);
	        return new ResponseEntity<>(updated, HttpStatus.OK);
    	} catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable("id") Long id) {
    	try {
	        ingredientService.deleteIngredient(id);
	        return ResponseEntity.noContent().build();
    	} catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }


    @GetMapping("/name/contains/{partialName}")
    public ResponseEntity<List<IngredientDTO>> getIngredientsByNameContaining(@PathVariable String partialName) {
    	try {
    		 List<Ingredient> ingredients = ingredientService.getIngredientsByNameContaining(partialName);
    		 List<IngredientDTO> ingredientDTOs = ingredients.stream()
 	                .map(this::convertToDTO)
 	                .collect(Collectors.toList());
    		 return ResponseEntity.ok(ingredientDTOs);
    	} catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }
    
    private IngredientDTO convertToDTO(Ingredient ingredient) {
    	IngredientDTO ingredientDTO = new IngredientDTO();
    	ingredientDTO.setId(ingredient.getId());
    	ingredientDTO.setName(ingredient.getName());
        return ingredientDTO;
    }

}
