package com.assignment.RecipeManager.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.RecipeManager.model.Ingredient;


@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long>{
	
//	get all
	List<Ingredient> findAll();
	
//	add an ingredient
	@SuppressWarnings("unchecked")
	Ingredient save(Ingredient ingredient);
	
//	get ingredients that contains partial ingredient name
    List<Ingredient> findByNameContainingIgnoreCase(String partialName);

}
