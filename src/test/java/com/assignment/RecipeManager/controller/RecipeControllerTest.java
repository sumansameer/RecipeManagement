package com.assignment.RecipeManager.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.assignment.RecipeManager.model.Recipe;
import com.assignment.RecipeManager.model.RecipeDTO;
import com.assignment.RecipeManager.service.RecipeService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class RecipeControllerTest {
	
	@InjectMocks
    private RecipeController recipeController;

    @Mock
    private RecipeService recipeService;

    
    @Test
    public void testAddRecipe_Success() {
        Recipe mockRecipe = new Recipe();
        mockRecipe.setName("Test Recipe");

        when(recipeService.addRecipe(any(Recipe.class))).thenReturn(mockRecipe);

        ResponseEntity<Recipe> responseEntity = recipeController.addRecipe(mockRecipe);

        verify(recipeService, times(1)).addRecipe(any(Recipe.class));

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(mockRecipe, responseEntity.getBody());
    }
    
    
    @Test
    public void testGetAllRecipes_Success() {
        List<Recipe> mockRecipes = new ArrayList<>();
        Recipe recipe1 = new Recipe();
        Recipe recipe2 = new Recipe();

        mockRecipes.add(recipe1);
        mockRecipes.add(recipe2);

        when(recipeService.getAllRecipes()).thenReturn(mockRecipes);

        ResponseEntity<List<RecipeDTO>> responseEntity = recipeController.getAllRecipes();

        verify(recipeService, times(1)).getAllRecipes();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<RecipeDTO> actualRecipeDTOs = responseEntity.getBody();
        assertEquals(mockRecipes.size(), actualRecipeDTOs.size());
    }
    
    
    @Test
    public void testUpdateRecipe_Success() {
        Recipe mockRecipe = new Recipe();
        mockRecipe.setId(1L);
        mockRecipe.setName("Updated Recipe");

        when(recipeService.updateRecipe(eq(1L), any(Recipe.class))).thenReturn(mockRecipe);

        ResponseEntity<Recipe> responseEntity = recipeController.updateRecipe(1L, mockRecipe);

        verify(recipeService, times(1)).updateRecipe(eq(1L), any(Recipe.class));

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockRecipe, responseEntity.getBody());
    }
    
    @Test
    public void testDeleteRecipe_Success() {
        ResponseEntity<Void> responseEntity = recipeController.deleteRecipe(1L);

        verify(recipeService, times(1)).deleteRecipe(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
    
    @Test
    public void testGetRecipeById_ExistingId_Success() {
        Recipe mockRecipe = new Recipe();
        mockRecipe.setId(1L);
        mockRecipe.setName("Recipe 1");

        when(recipeService.getRecipeById(1L)).thenReturn(mockRecipe);

        ResponseEntity<RecipeDTO> responseEntity = recipeController.getRecipeById(1L);

        verify(recipeService, times(1)).getRecipeById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockRecipe.getId(), responseEntity.getBody().getId());
        assertEquals(mockRecipe.getName(), responseEntity.getBody().getName());
    }
    
    @Test
    public void testGetRecipeById_NonExistingId_NotFound() {
        when(recipeService.getRecipeById(100L)).thenReturn(null);

        ResponseEntity<RecipeDTO> responseEntity = recipeController.getRecipeById(100L);

        verify(recipeService, times(1)).getRecipeById(100L);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
    
    @Test
    public void testGetVegetarianRecipes_Success() {
        List<Recipe> mockVegetarianRecipes = new ArrayList<>();
        Recipe recipe1 = new Recipe();
        Recipe recipe2 = new Recipe();
        mockVegetarianRecipes.add(recipe1);
        mockVegetarianRecipes.add(recipe2);

        when(recipeService.getVegetarianRecipes()).thenReturn(mockVegetarianRecipes);

        ResponseEntity<List<RecipeDTO>> responseEntity = recipeController.getVegetarianRecipes();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<RecipeDTO> actualRecipeDTOs = responseEntity.getBody();
        assertEquals(mockVegetarianRecipes.size(), actualRecipeDTOs.size());
    }
    
    @Test
    public void testGetRecipesByServings_Success() {
        int servings = 4;

        List<Recipe> mockRecipesByServings = new ArrayList<>();
        Recipe recipe1 = new Recipe();
        Recipe recipe2 = new Recipe();
        mockRecipesByServings.add(recipe1);
        mockRecipesByServings.add(recipe2);

        when(recipeService.getRecipesByServings(servings)).thenReturn(mockRecipesByServings);

        ResponseEntity<List<RecipeDTO>> responseEntity = recipeController.getRecipesByServings(servings);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<RecipeDTO> actualRecipeDTOs = responseEntity.getBody();
        assertEquals(mockRecipesByServings.size(), actualRecipeDTOs.size());
    }

}
