package com.example.homemadeproto.entity;


import enums.PortionSize;
import jakarta.persistence.*;

@Entity
public class ReviewDish extends Review{

    @ManyToOne(optional = false)
    @JoinColumn(name = "dish_idp", nullable = false)
    private Plat dish;

    private Boolean Delivery;

    private float serviceRating;
    private PortionSize portionSize;
    private Boolean priceSatisfaction;



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
    public float getServiceRating() {
        return serviceRating;
    }

    public void setServiceRating(float serviceRating) {
        this.serviceRating = serviceRating;
    }

    public PortionSize getPortionSize() {
        return portionSize;
    }

    public void setPortionSize(PortionSize portionSize) {
        this.portionSize = portionSize;
    }

    public Boolean getPriceSatisfaction() {
        return priceSatisfaction;
    }

    public void setPriceSatisfaction(Boolean priceSatisfaction) {
        this.priceSatisfaction = priceSatisfaction;
    }

    public Plat getDish() {
        return dish;
    }

    public void setDish(Plat dish) {
        this.dish = dish;
    }

}
