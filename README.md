# Recipe Manager Application

The Recipe Manager is a Java application that allows users to manage their favorite recipes. Users can add, update, remove, and fetch recipes. Additionally, they can filter available recipes based on various criteria such as vegetarian status, number of servings, specific ingredients, and text search within instructions.

## Table of Contents


1. [Features](#Features)
2. [Technologies Used](#technologies-used)
3. [Getting Started](#getting-started)
4. [Prerequisites](#prerequisites)
5. [Installation](#installation)
6. [Configuration](#configuration)
7. [Database Setup](#database-setup)
8. [Usage](#usage)
9. [Endpoints](#endpoints)
10. [Swagger Documentation](#swagger-documentation)
11. [Testing](#testing)

# Features

	• Manage recipes and ingredients
	• Support for adding, updating, deleting, and retrieving recipes and ingredients
	• Filtering recipes by various criteria such as vegetarian status, servings, ingredients, etc.


# Technologies Used

	• Spring Boot
	• Spring Data JPA 
	• PostgreSQL
	• Spring Web
	• Springdoc OpenAPI (Swagger)
	• JUnit
	• Mockito

# Getting Started

To get started with this project, follow these steps:

# Prerequisites

	• Java JDK 17 or higher installed
	• Maven installed
	• PostgreSQL database installed and running

# Installation
	• Clone the repository to your local machine.
	• Navigate to the project directory.
	• Run mvn clean install to build the project.

# Configuration

Configure the database connection in application.properties file.

# Database Setup

	• Install PostgreSQL if not already installed.
	• Create a new database named recipemanager using the PostgreSQL command line or a GUI tool like pgAdmin.
	• Configure the database connection details in the application.properties file.
	• Run the SQL script provided in create_tables_and_insert_data.sql to create the necessary tables and populate initial data.

# Usage
	• Run the application: mvn spring-boot:run or by executing the generated JAR file.
	• Access the application using the provided endpoints.

# Endpoints


	• POST /recipes: Create a new recipe.
	• GET /recipes: Retrieve all recipes.
	• PUT /recipes/{id}: Update a recipe by ID.
	• DELETE /recipes/{id}: Delete a recipe by ID.
	• GET /recipes/{id}: Retrieve a recipe by ID.
	• GET /recipes/vegetarian: Retrieve all vegetarian recipes.
	• GET /recipes/servings/{servings}: Retrieve recipes by servings.
	• GET /recipes/withIngredient: Retrieve recipes by ingredient. 
		○ Request Parameter: 
			§ ingredientName - Name of the ingredient.
	• GET /recipes/filtered: Retrieve filtered recipes. 
		○ Request Parameters:
			§ excludedIngredient (optional) - Name of the ingredient to exclude.
			§ instructionText (optional) - Text to search for in recipe instructions.
	• GET /recipes/servingsandingredient: Retrieve recipes by servings and ingredient.
		○ Request Parameters:
			§ servings (optional) - Number of servings.
			§ ingredient (optional) - Name of the ingredient.



	• POST /ingredients: Create a new ingredient.
	• GET /ingredients: Retrieve all ingredients.
	• GET /ingredients/{id}: Retrieve an ingredient by ID.
	• PUT /ingredients/{id}: Update an ingredient by ID.
	• DELETE /ingredients/{id}: Delete an ingredient by ID.
 	• GET /ingredients/name/contains/{partialName}: Retrieve ingredients by partial name.


# Swagger API Documentation

The application's REST API endpoints are documented using Swagger. Once the application is running, you can access the Swagger UI by navigating to http://localhost:8080/swagger-ui.html in your web browser.

# Testing

The application includes unit tests for controllers, services, and repositories. To run the tests, execute the following Maven command: mvn test


