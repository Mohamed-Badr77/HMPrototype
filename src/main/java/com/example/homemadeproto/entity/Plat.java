package com.example.homemadeproto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Plat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idP;

    @NotBlank(message = "Name is required")
    @Size(min=2,message="Name must have at least 2 characters")
    private String dishName;

    private float dishRating;

    @NotNull(message = "Prix requis")
    private float price;

    @Min(value = 1, message = "Prep time must at least be 1 minute")
    private int prepTimeMinutes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuisinier_id", nullable = false)
    private CuisinierProfile cuisinier;

    public Plat() {}

    public Plat(String DishName, float DishRating, float Price, int PrepTimeMinutes, CuisinierProfile cuisinier) {
        this.dishName = DishName;
        this.dishRating = DishRating;
        this.price = Price;
        this.prepTimeMinutes = PrepTimeMinutes;
        this.cuisinier = cuisinier;
    }


    public void setId(Long id) {
        this.idP = id;
    }

    public Long getId() {
        return idP;
    }

    public CuisinierProfile getCuisinier() {
        return cuisinier;
    }

    public void setCuisinier(CuisinierProfile cuisinier) {
        this.cuisinier = cuisinier;
    }

    public float getDishRating() {
        return dishRating;
    }

    public void setDishRating(float dishRating) {
        this.dishRating = dishRating;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getPrepTimeMinutes() {
        return prepTimeMinutes;
    }

    public void setPrepTimeMinutes(int prepTimeMinutes) {
        this.prepTimeMinutes = prepTimeMinutes;
    }
}
