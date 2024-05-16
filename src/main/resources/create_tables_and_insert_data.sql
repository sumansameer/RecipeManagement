-- Create table for recipes
CREATE TABLE recipes (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    instructions TEXT,
    servings INT NOT NULL,
    vegetarian BOOLEAN NOT NULL
);

-- Create table for ingredients
CREATE TABLE ingredients (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Create junction table for recipe-ingredients relationship
CREATE TABLE recipe_ingredients (
    recipe_id INT,
    ingredient_id INT,
    FOREIGN KEY (recipe_id) REFERENCES recipes(id),
    FOREIGN KEY (ingredient_id) REFERENCES ingredients(id),
    PRIMARY KEY (recipe_id, ingredient_id)
);

-- Insert dummy values into recipes table
INSERT INTO recipes (name, instructions, servings, vegetarian)
VALUES
    ('Spaghetti Carbonara', 'Cook pasta. Mix eggs, cheese, and cooked pancetta. Combine with pasta.', 4, false),
    ('Greek Salad', 'Chop tomatoes, cucumbers, onions, and olives. Add feta cheese and dressing.', 2, true),
    ('Vegetable Stir-Fry', 'Stir-fry mixed vegetables with garlic, ginger, and soy sauce. Serve with rice.', 3, true);

-- Insert dummy values into ingredients table
INSERT INTO ingredients (name)
VALUES
    ('Pasta'),
    ('Eggs'),
    ('Parmesan Cheese'),
    ('Pancetta'),
    ('Tomatoes'),
    ('Cucumbers'),
    ('Onions'),
    ('Olives'),
    ('Feta Cheese'),
    ('Garlic'),
    ('Ginger'),
    ('Soy Sauce'),
    ('Rice');

-- Insert dummy values into recipe_ingredients table
INSERT INTO recipe_ingredients (recipe_id, ingredient_id)
VALUES
    (1, 1), (1, 2), (1, 3), (1, 4),
    (2, 5), (2, 6), (2, 7), (2, 8), (2, 9),
    (3, 10), (3, 11), (3, 12), (3, 13);