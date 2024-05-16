package com.assignment.RecipeManager.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.assignment.RecipeManager.model.Recipe;
import com.assignment.RecipeManager.repository.RecipeRepository;

@SpringBootTest
class RecipeServiceTest {

	@Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeService recipeService;
    
    @Test
    void testAddRecipe_Success() {
        Recipe mockRecipe = new Recipe();
        mockRecipe.setId(1L);
        mockRecipe.setName("Test Recipe");

        when(recipeRepository.save(any(Recipe.class))).thenReturn(mockRecipe);

        Recipe savedRecipe = recipeService.addRecipe(mockRecipe);

        verify(recipeRepository, times(1)).save(mockRecipe);
        assertEquals(mockRecipe.getId(), savedRecipe.getId());
        assertEquals(mockRecipe.getName(), savedRecipe.getName());
    }
    
    @Test
    void testGetAllRecipes_Success() {
        Recipe mockRecipe1 = new Recipe();
        mockRecipe1.setId(1L);
        mockRecipe1.setName("Recipe 1");

        Recipe mockRecipe2 = new Recipe();
        mockRecipe2.setId(2L);
        mockRecipe2.setName("Recipe 2");

        List<Recipe> mockRecipes = Arrays.asList(mockRecipe1, mockRecipe2);

        when(recipeRepository.findAll()).thenReturn(mockRecipes);

        List<Recipe> retrievedRecipes = recipeService.getAllRecipes();

        verify(recipeRepository, times(1)).findAll();
        assertEquals(2, retrievedRecipes.size());
    }
    
    @Test
    void testUpdateRecipe_Success() {
        Recipe mockRecipe = new Recipe();
        mockRecipe.setId(1L);
        mockRecipe.setName("Test Recipe");

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(mockRecipe));
        when(recipeRepository.save(any(Recipe.class))).thenReturn(mockRecipe);

        Recipe updatedRecipe = recipeService.updateRecipe(1L, mockRecipe);

        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
        assertEquals(mockRecipe.getId(), updatedRecipe.getId());
        assertEquals(mockRecipe.getName(), updatedRecipe.getName());
    }
    
    @Test
    void testDeleteRecipe_Success() {
        doNothing().when(recipeRepository).deleteById(anyLong());

        recipeService.deleteRecipe(1L);

        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
    
    @Test
    void testGetRecipeById_Success() {
        Recipe mockRecipe = new Recipe();
        mockRecipe.setId(1L);
        mockRecipe.setName("Test Recipe");

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(mockRecipe));

        Recipe retrievedRecipe = recipeService.getRecipeById(1L);

        verify(recipeRepository, times(1)).findById(anyLong());
        assertEquals(mockRecipe.getId(), retrievedRecipe.getId());
        assertEquals(mockRecipe.getName(), retrievedRecipe.getName());
    }
    
    @Test
    void testGetVegetarianRecipes_Success() {
        Recipe mockRecipe = new Recipe();
        mockRecipe.setId(1L);
        mockRecipe.setName("Vegetarian Recipe");
        mockRecipe.setVegetarian(true);

        when(recipeRepository.findByVegetarian(true)).thenReturn(List.of(mockRecipe));

        List<Recipe> vegetarianRecipes = recipeService.getVegetarianRecipes();

        verify(recipeRepository, times(1)).findByVegetarian(true);
        assertEquals(1, vegetarianRecipes.size());
        assertEquals(mockRecipe.getId(), vegetarianRecipes.get(0).getId());
        assertEquals(mockRecipe.getName(), vegetarianRecipes.get(0).getName());
    }

}
