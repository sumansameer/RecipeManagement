package com.assignment.RecipeManager.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import com.assignment.RecipeManager.model.Ingredient;
import com.assignment.RecipeManager.model.IngredientDTO;
import com.assignment.RecipeManager.service.IngredientService;

import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class IngredientControllerTest {
	
	
	 	@InjectMocks
	    private IngredientController ingredientController;

	    @Mock
	    private IngredientService ingredientService;

	    @Test
	    public void testAddIngredient() {
	        
	        Ingredient mockIngredient = new Ingredient();
	        mockIngredient.setName("Test Ingredient");

	        when(ingredientService.addIngredient(any(Ingredient.class))).thenReturn(mockIngredient);

	        ReflectionTestUtils.setField(ingredientController, "ingredientService", ingredientService);

	        ResponseEntity<Ingredient> responseEntity = ingredientController.addIngredient(mockIngredient);

	        verify(ingredientService, times(1)).addIngredient(any(Ingredient.class));

	        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	        assertEquals(mockIngredient, responseEntity.getBody());
	    }
	    
	    
	    @Test
	    public void testGetAllIngredients() {
	    	 Ingredient mockIngredient1 = new Ingredient();
	         mockIngredient1.setId(1L);
	         mockIngredient1.setName("Ingredient 1");

	         Ingredient mockIngredient2 = new Ingredient();
	         mockIngredient2.setId(2L);
	         mockIngredient2.setName("Ingredient 2");

	         List<Ingredient> mockIngredients = Arrays.asList(mockIngredient1, mockIngredient2);

	         when(ingredientService.getAllIngredients()).thenReturn(mockIngredients);

	         ResponseEntity<List<IngredientDTO>> responseEntity = ingredientController.getAllIngredients();

	         verify(ingredientService, times(1)).getAllIngredients();
	         assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	         assertEquals(2, responseEntity.getBody().size());
	    }

	    @Test
	    public void testDeleteIngredient() {
	        ResponseEntity<Void> responseEntity = ingredientController.deleteIngredient(1L);

	        verify(ingredientService, times(1)).deleteIngredient(1L);

	        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
	    }
	    
	    
	    @Test
	    public void testGetIngredientById() {
	    	Ingredient mockIngredient = new Ingredient();
	        mockIngredient.setId(1L);
	        mockIngredient.setName("Test Ingredient");

	        when(ingredientService.getIngredientById(1L)).thenReturn(mockIngredient);

	        ResponseEntity<IngredientDTO> responseEntity = ingredientController.getIngredientById(1L);

	        verify(ingredientService, times(1)).getIngredientById(1L);
	        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	        assertEquals(mockIngredient.getId(), responseEntity.getBody().getId());
	        assertEquals(mockIngredient.getName(), responseEntity.getBody().getName());
	    }
	    
	    
	    @Test
	    public void testUpdateIngredient() {
	        Ingredient mockIngredient = new Ingredient();
	        mockIngredient.setId(1L);
	        mockIngredient.setName("Test Ingredient");

	        when(ingredientService.updateIngredient(anyLong(), any(Ingredient.class))).thenReturn(mockIngredient);

	        ResponseEntity<Ingredient> responseEntity = ingredientController.updateIngredient(1L, mockIngredient);

	        verify(ingredientService, times(1)).updateIngredient(anyLong(), any(Ingredient.class));

	        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

	        assertEquals(mockIngredient, responseEntity.getBody());
	    }
	    
	    
	    @Test
	    public void testGetIngredientsByNameContaining() {
	    	 Ingredient mockIngredient1 = new Ingredient();
	         mockIngredient1.setId(1L);
	         mockIngredient1.setName("Ingredient 1");

	         Ingredient mockIngredient2 = new Ingredient();
	         mockIngredient2.setId(2L);
	         mockIngredient2.setName("Ingredient 2");

	         List<Ingredient> mockIngredients = Arrays.asList(mockIngredient1, mockIngredient2);

	         when(ingredientService.getIngredientsByNameContaining("Ingredient")).thenReturn(mockIngredients);

	         ResponseEntity<List<IngredientDTO>> responseEntity = ingredientController.getIngredientsByNameContaining("Ingredient");

	         verify(ingredientService, times(1)).getIngredientsByNameContaining("Ingredient");
	         assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	         assertEquals(2, responseEntity.getBody().size());
	    }

}
