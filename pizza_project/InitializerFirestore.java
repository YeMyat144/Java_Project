package com.firsttime_pizza;

import java.io.FileInputStream;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;

public class InitializerFirestore {
    private Firestore db;
    public InitializerFirestore(){
    try{
    FileInputStream serviceAccount = new FileInputStream("src/main/resources/firebase_java_json_file.json");
    FirestoreOptions firestoreOptions = FirestoreOptions.newBuilder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .build();
    this.db = firestoreOptions.getService();

    }catch(Exception e){

    }
}

public Firestore getDB() {
        return db;
    }

    
}
