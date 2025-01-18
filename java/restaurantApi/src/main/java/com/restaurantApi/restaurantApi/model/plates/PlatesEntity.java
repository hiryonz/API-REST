package com.restaurantApi.restaurantApi.model.plates;

import com.restaurantApi.restaurantApi.model.categories.CategoriesEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_plates;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    //@Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private int availability;

    //categoria fk
    @ManyToOne()
    @JoinColumn(name = "id_category")
    private CategoriesEntity categoriesEntity;
}
