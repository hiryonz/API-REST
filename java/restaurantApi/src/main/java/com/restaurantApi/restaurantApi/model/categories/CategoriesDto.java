package com.restaurantApi.restaurantApi.model.categories;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriesDto {
    private Long id_category;
    private String name;
    private String description;
}
