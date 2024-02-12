package com.firsttime_pizza;

import java.util.List;

public class DrinksOrder {
    private String pizzaVariety;
    private List<String> toppings;
    private int numberOfPizzas; 
    private double itemPrice;

    public DrinksOrder(String pizzaVariety, List<String> toppings, int numberOfPizzas) {
        this.pizzaVariety = pizzaVariety;
        this.toppings = toppings;
        this.numberOfPizzas = numberOfPizzas;
    }

    public String getPizzaVariety() {
        return pizzaVariety;
    }

    public List<String> getToppings() {
        return toppings;
    }

    public int getNumberOfPizzas() {
        return numberOfPizzas;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
}

