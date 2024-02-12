package com.firsttime_pizza;

import java.util.HashMap;
import java.util.Map;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;

public class Main {
    public static void main(String[] args) {
        try{
             Firestore db = new InitializerFirestore().getDB();
             System.out.println(db);
             Map<String, Object> items = new HashMap<>();
             items.put("test_name","La Wee");
             CollectionReference itemsCollection = db.collection("Test");
             ApiFuture<DocumentReference> future = itemsCollection.add(items);
             future.get();
        }catch(Exception e){
            e.printStackTrace();
        }
       
    }
}