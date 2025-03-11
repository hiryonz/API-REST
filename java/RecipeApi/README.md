# Recipe Management API

A RESTful API for managing recipes and their ingredients. This API allows users to create, read, update, and delete recipes along with their associated ingredients.

## Data Models

### Recipe

The main entity representing a cooking recipe.

```java
  public class RecipeDTO {
      private Long id;
      private String title;
      private String description;
      private String steps;
      private List<IngredientDTO> ingredient;
  }
  
  public class IngredientDTO {
      private Long id;
      private String ingredient;
      private float quantity;
  }
```

## API Endpoints

The Recipe Management API provides the following endpoints for managing recipes and their ingredients:

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /recipe | Get all recipes |
| GET | /recipe/{id} | Get a specific recipe by ID |
| PUT | /recipe/save | Create or update a recipe |
| DELETE | /recipe/{id} | Delete a recipe by ID |

### GET /recipe

Retrieves a list of all recipes.

*Response:*
- Status Code: 200 OK (Success)
- Status Code: 204 NO_CONTENT (No recipes found)

*Response Body Example:*
```json
[
  {
    "id": 1,
    "title": "Spaghetti Carbonara",
    "description": "Classic Italian pasta dish",
    "steps": "1. Cook pasta until al dente.",
    "ingredient": [
      {
        "id": 1,
        "ingredient": "Spaghetti",
        "quantity": 500
      },
    ]
  },
  {
    "id": 2,
    "title": "Chocolate Cake",
    "description": "Rich chocolate dessert",
    "steps": "1. Mix dry ingredients.\n2. Add wet ingredients.\n3. Bake at 350°F for 30 minutes.",
    "ingredient": [
      {
        "id": 3,
        "ingredient": "Flour",
        "quantity": 250
      },
    ]
  }
]
```

### GET /recipe/id

Retrieves a recipes by id.

*Response:*
- Status Code: 200 OK (Success)
- Status Code: 404 NOT_FOUND (No recipes found)

*Response Body Example:*
```json
{
  "id": 1,
  "title": "Spaghetti Carbonara",
  "description": "Classic Italian pasta dish",
  "steps": "1. Cook pasta until al dente.",
  "ingredient": [
    {
      "id": 1,
      "ingredient": "Spaghetti",
      "quantity": 500
    },
  ]
}
```

### PUT /recipe/save

Created and update a recipe.

*Response:*
- Status Code: 200 OK (Success/UPDATED)
- Status Code: 201 CREATED (CREATED/INSERT)

*Response Body Example:*
```json
{
  "id": null, // Omit or set to null for creating a new recipe, include for updating
  "title": "Spaghetti Carbonara",
  "description": "Classic Italian pasta dish",
  "steps": "1. Cook pasta until al dente.",
  "ingredient": [
    {
      "ingredient": "Spaghetti",
      "quantity": 500
    },
  ]
}
```

### DELETE /recipe/id

Deletes a recipe by its ID.

*Parameters:*

- id (path parameter): The ID of the recipe to delete


*Response:*

- Status Code: 200 OK (Recipe successfully deleted)
- Status Code: 404 NOT_FOUND (Recipe not found)

