package com.restaurantApi.restaurantApi.model.plates;

import com.restaurantApi.restaurantApi.model.categories.CategoriesDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlatesDto {
    private Long id_plates;
    private String name;
    private String description;
    private Double price;
    private String image;
    private int availability;
    private Long id_categories;
}
