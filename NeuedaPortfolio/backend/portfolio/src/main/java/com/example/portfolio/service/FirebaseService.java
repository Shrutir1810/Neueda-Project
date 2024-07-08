package com.example.portfolio.service;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;
import com.example.portfolio.objects.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
//import com.google.cloud.firestore.DocumentReference;
//import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class FirebaseService {
    public String saveUserDetails(User user) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("users").document(user.getName()).set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();


    }

    public User getUserDetails(String name) throws InterruptedException,ExecutionException{
        Firestore dbFirestore=FirestoreClient.getFirestore();
        DocumentReference documentReference=dbFirestore.collection("users").document(name);
        ApiFuture<DocumentSnapshot> future=documentReference.get();
        DocumentSnapshot document=future.get();
        User user=null;
        if(document.exists()){
            user=document.toObject(User.class);
            return user;
        }
        else{
            return null;
        }
    }


    public String updateUserDetails(User user) throws InterruptedException,ExecutionException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("users").document(user.getName()).set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();

    }
    
    public String deleteUserDetails(String name){
        Firestore dbFirestore=FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult=dbFirestore.collection("users").document(name).delete();
        return "Document with Name "+name+" has been deleted";
    }




}
