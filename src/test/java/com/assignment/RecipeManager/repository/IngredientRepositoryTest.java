package com.assignment.RecipeManager.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.assignment.RecipeManager.model.Ingredient;

@ExtendWith(MockitoExtension.class)
class IngredientRepositoryTest {

	@Mock
    private IngredientRepository ingredientRepository;
    
    @Test
    void testFindAll() {       
        List<Ingredient> mockIngredients = new ArrayList<>();
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        ingredient1.setName("Salt");

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        ingredient2.setName("Pepper");

        mockIngredients.add(ingredient1);
        mockIngredients.add(ingredient2);

        when(ingredientRepository.findAll()).thenReturn(mockIngredients);

        List<Ingredient> allIngredients = ingredientRepository.findAll();

        assertEquals(2, allIngredients.size());
        assertEquals("Salt", allIngredients.get(0).getName());
        assertEquals("Pepper", allIngredients.get(1).getName());
    }
    
    @Test
    void testSave() {
        Ingredient ingredient = new Ingredient();
        ingredient.setName("Sugar");

        when(ingredientRepository.save(any(Ingredient.class))).thenReturn(ingredient);

        Ingredient savedIngredient = ingredientRepository.save(new Ingredient());

        assertEquals("Sugar", savedIngredient.getName());
    }
    
    @Test
    void testFindByNameContainingIgnoreCase() {
        String partialName = "sugar";
        
        List<Ingredient> mockIngredients = new ArrayList<>();
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        ingredient1.setName("Brown Sugar");

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        ingredient2.setName("White Sugar");

        mockIngredients.add(ingredient1);
        mockIngredients.add(ingredient2);

        when(ingredientRepository.findByNameContainingIgnoreCase(partialName)).thenReturn(mockIngredients);

        List<Ingredient> foundIngredients = ingredientRepository.findByNameContainingIgnoreCase(partialName);

        assertEquals(2, foundIngredients.size());
        assertEquals("Brown Sugar", foundIngredients.get(0).getName());
        assertEquals("White Sugar", foundIngredients.get(1).getName());
    }

}
