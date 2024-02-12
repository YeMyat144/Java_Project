package com.firsttime_pizza;

import javax.swing.*;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrinksApplication {

    public static String title = "Welcome to Bubble Boulevard";
    public static String noOfPizzas = "Number of Drinks";
    public static String pizzaSize = "Drinks Size";
    public static String regular = "Regular";
    public static String small = "Small";
    public static String large = "Large";
    public static String medium = "Medium";

    public static String pizzaSizeMessage = "Please Choose a Drinks Size!!!";
    public static String pizzaSizeMessageWindow = "You Didn't Choose a Size!!!";
    public static String noOfPizzasMessage = "Please enter the number of Drinks!!!";

    public static String toppings = "Toppings:";

    public static String Boba = "Boba";
    public static String Grass_Jelly = "Grass Jelly";
    public static String Oreo_Crumbs = "Oreo Crumbs";
    public static String Fruit_Jelly = "Fruit Jelly";
    public static String Aloe_Vera = "Aloe Vera";

    // File path for storing user data
  
    // Create a list to store user accounts
    private static List<UserAccount> userAccounts = new ArrayList<>();
  
    public static void main(String[] args) {
        // Load user data from the file when the application starts

        // Create a login frame
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setTitle("Login to Bubble Boulevard");
        loginFrame.setSize(400, 200);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);
        loginFrame.setLocationRelativeTo(null);
    }

    // Method to add a new user account to the list
    public static void addUserAccount(String username, String password) {
        try{
        Firestore db = new InitializerFirestore().getDB();
        Map<String, Object> user = new HashMap<>();
        user.put("name",username);
        user.put("password",password);
        CollectionReference userCollectionReference = db.collection("Users");
        ApiFuture<DocumentReference> future = userCollectionReference.add(user);
        future.get();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // Method to check if a user account exists
    public static boolean userExists(String username, String password) {

        try {
            Firestore db = new InitializerFirestore().getDB();
            CollectionReference userReference = db.collection("Users");
            ApiFuture<QuerySnapshot> querySnapshot = userReference.get();
            for(QueryDocumentSnapshot document : querySnapshot.get().getDocuments()){
                String name = document.getString("name");
                String pass = document.getString("password");
                UserAccount userAccount = new UserAccount(name, pass);
                userAccounts.add(userAccount);
            }

            for (UserAccount user : userAccounts) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

 
}
