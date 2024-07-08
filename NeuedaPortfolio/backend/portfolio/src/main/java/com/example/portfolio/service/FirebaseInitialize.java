package com.example.portfolio.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class FirebaseInitialize {

   @PostConstruct
   public void initialize() {
      try {
         FileInputStream serviceAccount = new FileInputStream("D:\\Citi\\CitiNeuedaPortfolio\\backend\\portfolio\\serviceAccountKey.json");
         FirebaseOptions options = new FirebaseOptions.Builder()
             .setCredentials(GoogleCredentials.fromStream(serviceAccount))
             .setDatabaseUrl("https://portfoliomanagement-549d0.firbaseio.com")
             .build();

         // Initialize Firebase App only if it is not already initialized
         if (FirebaseApp.getApps().isEmpty()) {
             FirebaseApp.initializeApp(options);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
