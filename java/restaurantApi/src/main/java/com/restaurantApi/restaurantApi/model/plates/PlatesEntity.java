package com.restaurantApi.restaurantApi.model.plates;

import com.restaurantApi.restaurantApi.model.categories.CategoriesEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder

@Data
@Entity
@Table(name = "plates")
public class PlatesEntity {
    @Id
    private Long id_plates;
    private String name;
    private String description;
    private Double price;
    private String image;
    private int availability;

    //categoria fk
    @ManyToOne()
    @JoinColumn(name = "id_category")
    private CategoriesEntity categoriesEntity;
}
