package com.restaurantApi.restaurantApi.model.categories;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder

@Data
@Entity
@Table(name = "category")
public class CategoriesEntity {
    @Id
    private Long id_category;
    private String name;
    private String description;

    
}
