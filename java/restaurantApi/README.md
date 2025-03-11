# Restaurant Management API

A RESTful API for managing a restaurant (categories, custumers, orders, items). This API allows users to create, read, update, and delete all the above models.

## Data Models

### Restaurant


```java
public class CategoriesDto {
    private Long id_category;
    private String name;
    private String description;
}

public class CustumerDto {
    private Long id_custumer;
    private String name;
    private String email;
    private int phone;
    private String address;
    private List<OrdersDto> orders;
}

public class OrderItemsDto {
    private Long id_orderItem;
    //fk with orders
    private Long id_orders;
    //fk with plates
    private PlatesDto plates;
}

public class OrdersDto {
    private Long id_orders;
    private Date order_date;
    private double total;
    private int status; //enum (pendiente, preparacion, listo, entregado)
    private List<OrderItemsDto> orderItems;
    //id_custumer fk
    private Long id_custumer;
}

public class PlatesDto {
    private Long id_plates;
    private String name;
    private String description;
    private Double price;
    private String image;
    private int availability;
    private Long id_categories;
}
```

## API Endpoints (PLATES)

The Restaurant Management API provides the following endpoints for managing plates:

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /plates | Get all plates |
| GET | /plates/{id} | Get a specific plate by ID |
| POST | /plates | Created a recipe |
| PUT | /plates/{id} | update a plate |
| DELETE | /plates/{id} | Delete a plate by ID |

### GET /plates

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
