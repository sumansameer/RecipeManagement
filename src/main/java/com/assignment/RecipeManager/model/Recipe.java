package com.assignment.RecipeManager.model;

import java.util.Set;

import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "recipes")
public class Recipe {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;

	    @Column(length = 1000)
	    private String instructions;

	    private int servings;

	    private boolean vegetarian;
	    
	    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
	    private Set<RecipeIngredient> recipeIngredients;

	 // Getters and setters
	    
	    
	    
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getInstructions() {
			return instructions;
		}

		public void setInstructions(String instructions) {
			this.instructions = instructions;
		}

		public int getServings() {
			return servings;
		}

		public void setServings(int servings) {
			this.servings = servings;
		}

		public boolean isVegetarian() {
			return vegetarian;
		}

		public void setVegetarian(boolean vegetarian) {
			this.vegetarian = vegetarian;
		}	 
		
		public Set<RecipeIngredient> getRecipeIngredients() {
			return recipeIngredients;
		}

		public void setRecipeIngredients(Set<RecipeIngredient> recipeIngredients) {
			this.recipeIngredients = recipeIngredients;
		}

}
