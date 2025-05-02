package com.example.homemadeproto.entity;


import jakarta.persistence.*;

@Entity
public class ReviewDish extends Review{

    @ManyToOne
    private Plat dish;

    private Boolean Delivery;

    public ReviewDish() {
        super();
    }
    public ReviewDish(String dishName, Boolean delivery) {}
    public Boolean getDelivery() {
        return Delivery;
    }
    public void setDelivery(Boolean delivery) {
        Delivery = delivery;
    }

    public Plat getDish() {
        return dish;
    }

    public void setDish(Plat dish) {
        this.dish = dish;
    }

}
