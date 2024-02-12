package com.firsttime_pizza;

import javax.swing.*;

public class OrderApp {
    public static String title = "Bubble Boulevard - Order Drinks";
    public static String pizzaSize = "Drinks Size";
    public static String small = "Small";
    public static String medium = "Medium";
    public static String large = "Large";
    
    public static String toppings = "Toppings";
    public static String Boba = "Boba";
    public static String Grass_Jelly = "Grass Jelly";
    public static String Oreo_Crumbs = "Oreo Crumbs";
    public static String Fruit_Jelly = "Fruit Jelly";
    public static String Aloe_Jelly = "Aloe Jelly";
    public static String calculateButtonLabel = "Calculate Total";
    public static String resetButtonLabel = "Reset";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(title);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            DrinksPage pizzaPage = new DrinksPage();
            frame.add(pizzaPage);
            frame.setSize(760, 760);
            frame.setResizable(false);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        });
    }
}



