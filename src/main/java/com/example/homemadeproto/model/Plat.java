package com.example.homemadeproto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Plat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idP;

    @NotBlank(message = "Name is required")
    @Size(min=2,message="Name must have at least 2 characters")

    private String DishName;

    public float getDishRating() {
        return DishRating;
    }

    public void setDishRating(float dishRating) {
        DishRating = dishRating;
    }

    public String getDishName() {
        return DishName;
    }

    public void setDishName(String dishName) {
        DishName = dishName;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public int getPrepTimeMinutes() {
        return PrepTimeMinutes;
    }

    public void setPrepTimeMinutes(int prepTimeMinutes) {
        PrepTimeMinutes = prepTimeMinutes;
    }

    private float  DishRating;

    @NotNull(message = "Prix requis")

    private float Price;

    @Min(value = 1, message = "Prep time must at least be 1 minute")

    private int PrepTimeMinutes;

    public Plat() {}

    public Plat(String DishName, float DishRating, float Price, int PrepTimeMinutes) {
        this.DishName = DishName;
        this.DishRating = DishRating;
        this.Price = Price;
    }


    public void setId(Long id) {
        this.idP = id;
    }

    public Long getId() {
        return idP;
    }
}
