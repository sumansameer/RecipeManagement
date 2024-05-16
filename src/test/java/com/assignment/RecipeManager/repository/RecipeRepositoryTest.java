package com.assignment.RecipeManager.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.assignment.RecipeManager.model.Recipe;

@ExtendWith(MockitoExtension.class)
class RecipeRepositoryTest {

	@Mock
    private RecipeRepository recipeRepository;
	
	@Test
    void testFindAll() {
        Recipe recipe1 = new Recipe();
        Recipe recipe2 = new Recipe();
        when(recipeRepository.findAll()).thenReturn(Arrays.asList(recipe1, recipe2));

        List<Recipe> recipes = recipeRepository.findAll();

        assertEquals(2, recipes.size());
    }
	
	@Test
    void testFindByVegetarian() {
        Recipe recipe1 = new Recipe();
        Recipe recipe2 = new Recipe();
        when(recipeRepository.findByVegetarian(true)).thenReturn(Arrays.asList(recipe1, recipe2));

        List<Recipe> recipes = recipeRepository.findByVegetarian(true);

        assertEquals(2, recipes.size());
    }
	
	 @Test
	    void testFindByServings() {
	        Recipe recipe1 = new Recipe();
	        recipe1.setServings(4);
	        Recipe recipe2 = new Recipe();
	        recipe2.setServings(6);
	        when(recipeRepository.findByServings(4)).thenReturn(Arrays.asList(recipe1));

	        List<Recipe> recipes = recipeRepository.findByServings(4);

	        assertEquals(1, recipes.size());
	        assertEquals(4, recipes.get(0).getServings());
	    }
}
