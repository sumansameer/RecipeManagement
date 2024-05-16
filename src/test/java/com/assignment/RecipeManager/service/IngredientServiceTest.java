package com.assignment.RecipeManager.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.assignment.RecipeManager.model.Ingredient;
import com.assignment.RecipeManager.repository.IngredientRepository;

@SpringBootTest
class IngredientServiceTest {

	@Mock
    private IngredientRepository ingredientRepository;

    @InjectMocks
    private IngredientService ingredientService;
    
    @Test
    public void testAddIngredient_Success() {
        Ingredient mockIngredient = new Ingredient();
        mockIngredient.setId(1L);
        mockIngredient.setName("Test Ingredient");

        when(ingredientRepository.save(any(Ingredient.class))).thenReturn(mockIngredient);

        Ingredient result = ingredientService.addIngredient(mockIngredient);

        verify(ingredientRepository, times(1)).save(any(Ingredient.class));

        assertNotNull(result);
        assertEquals(mockIngredient.getId(), result.getId());
        assertEquals(mockIngredient.getName(), result.getName());
    }
    
    @Test
    public void testGetAllIngredients_Success() {
        List<Ingredient> mockIngredients = new ArrayList<>();
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        ingredient1.setName("Ingredient 1");

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        ingredient2.setName("Ingredient 2");

        mockIngredients.add(ingredient1);
        mockIngredients.add(ingredient2);

        when(ingredientRepository.findAll()).thenReturn(mockIngredients);

        List<Ingredient> result = ingredientService.getAllIngredients();

        verify(ingredientRepository, times(1)).findAll();

        assertNotNull(result);
        assertEquals(mockIngredients.size(), result.size());
    }
    
    @Test
    public void testGetIngredientById_Success() {
        Long id = 1L;
        Ingredient mockIngredient = new Ingredient();
        mockIngredient.setId(id);
        mockIngredient.setName("Test Ingredient");

        when(ingredientRepository.findById(id)).thenReturn(Optional.of(mockIngredient));

        Ingredient result = ingredientService.getIngredientById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Test Ingredient", result.getName());
    }
    
    @Test
    public void testUpdateIngredient_Success() {
        Long id = 1L;
        Ingredient existingIngredient = new Ingredient();
        existingIngredient.setId(id);
        existingIngredient.setName("Existing Ingredient");

        Ingredient updatedIngredient = new Ingredient();
        updatedIngredient.setId(id);
        updatedIngredient.setName("Updated Ingredient");

        when(ingredientRepository.findById(id)).thenReturn(Optional.of(existingIngredient));
        when(ingredientRepository.save(any(Ingredient.class))).thenReturn(updatedIngredient);

        Ingredient result = ingredientService.updateIngredient(id, updatedIngredient);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Updated Ingredient", result.getName());
    }
    
    @Test
    public void testDeleteIngredient_Success() {
        Long id = 1L;
        doNothing().when(ingredientRepository).deleteById(id);

        assertDoesNotThrow(() -> ingredientService.deleteIngredient(id));
    }
    
    @Test
    public void testGetIngredientsByNameContaining_Success() {
        String partialName = "Test";
        List<Ingredient> mockIngredients = new ArrayList<>();
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        ingredient1.setName("Test Ingredient 1");

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        ingredient2.setName("Another test ingredient");
        
        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);
        ingredient3.setName("Test Ingredient 2");

        mockIngredients.add(ingredient1);
        mockIngredients.add(ingredient2);
        mockIngredients.add(ingredient3);

        when(ingredientRepository.findByNameContainingIgnoreCase(partialName)).thenReturn(mockIngredients);

        List<Ingredient> result = ingredientService.getIngredientsByNameContaining(partialName);
        result.forEach((res)-> {System.out.println(res.getName());});
        assertNotNull(result);
        assertEquals(3, result.size()); 
    }

}
