package com.assignment.RecipeManager.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.assignment.RecipeManager.model.Recipe;
import com.assignment.RecipeManager.model.RecipeDTO;
import com.assignment.RecipeManager.service.RecipeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
	
	@Autowired
    private RecipeService recipeService;	

    @PostMapping
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
    	try {
	    	Recipe addedRecipe = recipeService.addRecipe(recipe);
	        return ResponseEntity.status(HttpStatus.CREATED).body(addedRecipe);
    	} catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }

    @GetMapping
    public ResponseEntity<List<RecipeDTO>> getAllRecipes() {
    	try {
	        List<Recipe> recipes = recipeService.getAllRecipes();
	        System.out.println("recieved" + recipes);
	        List<RecipeDTO> recipeDTOs = recipes.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	        return ResponseEntity.ok(recipeDTOs);
    	} catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody Recipe updatedRecipe) {
    	try {
	    	Recipe updated = recipeService.updateRecipe(id, updatedRecipe);
	        return new ResponseEntity<>(updated, HttpStatus.OK);
    	} catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable("id") Long id) {
    	try {
	        recipeService.deleteRecipe(id);
	        return ResponseEntity.noContent().build();
    	} catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> getRecipeById(@PathVariable("id") Long id) {
    	try { 
	    	Recipe recipe = recipeService.getRecipeById(id);
	        if (recipe != null) {
	        	RecipeDTO recipeDTO = convertToDTO(recipe);
	            return ResponseEntity.ok(recipeDTO);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
    	} catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }
    
    @GetMapping("/vegetarian")
    public ResponseEntity<List<RecipeDTO>> getVegetarianRecipes() {
    	try {
	    	List<Recipe> recipes = recipeService.getVegetarianRecipes();
	    	List<RecipeDTO> recipeDTOs = recipes.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	        return ResponseEntity.ok(recipeDTOs);
    	} catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }

    @GetMapping("/servings/{servings}")
    public ResponseEntity<List<RecipeDTO>> getRecipesByServings(@PathVariable int servings) {
    	try { 
	    	List<Recipe> recipes = recipeService.getRecipesByServings(servings);
	    	List<RecipeDTO> recipeDTOs = recipes.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	        return ResponseEntity.ok(recipeDTOs);
    	} catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }

    @GetMapping("/withIngredient")
    public ResponseEntity<List<RecipeDTO>>  getRecipesByIngredient(@RequestParam String ingredientName) {
    	try {
	    	List<Recipe> recipes = recipeService.getRecipesWithIngredient(ingredientName);
	    	recipes.forEach((recipe)-> {
	    		System.out.println(recipe.getName());
	    	});
	    	List<RecipeDTO> recipeDTOs = recipes.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	    	
	    	
	        return ResponseEntity.ok(recipeDTOs);
    	} catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
        
    }

    @GetMapping("/filtered")
    public ResponseEntity<List<RecipeDTO>> getFilteredRecipes(
            @RequestParam(required = false) String excludedIngredient,
            @RequestParam(required = false) String instructionText
    ) { 
    	try {
	    	List<Recipe> recipes = recipeService.getRecipesWithoutIngredientAndWithInstructionsContaining(excludedIngredient, instructionText);
	    	List<RecipeDTO> recipeDTOs = recipes.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	    	
	    	
	        return ResponseEntity.ok(recipeDTOs);
    	} catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
       
    }
    
    @GetMapping("/servingsandingredient")
    public ResponseEntity<List<RecipeDTO>> getServingsAndIngredient(
            @RequestParam(required = false) int servings,
            @RequestParam(required = false) String ingredient
    ) {
    	try {
	    	List<Recipe> recipes = recipeService.getServingsAndIngredient(servings, ingredient);
	    	List<RecipeDTO> recipeDTOs = recipes.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	    	
	    	
	        return ResponseEntity.ok(recipeDTOs);
    	} catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
       
    }
    
    
    
    private RecipeDTO convertToDTO(Recipe recipe) {
    	RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId(recipe.getId());
        recipeDTO.setName(recipe.getName());
        recipeDTO.setInstructions(recipe.getInstructions());
        recipeDTO.setServings(recipe.getServings());
        recipeDTO.setVegetarian(recipe.isVegetarian());
        return recipeDTO;
    }
	
}
