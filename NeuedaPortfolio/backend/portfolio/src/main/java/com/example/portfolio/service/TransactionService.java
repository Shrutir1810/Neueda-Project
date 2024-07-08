package com.example.portfolio.service;

import java.util.concurrent.ExecutionException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.portfolio.objects.Transaction;
import com.example.portfolio.objects.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import java.util.HashMap;


@Service
public class TransactionService {

    @Autowired
    private FirebaseService firebaseService;

    public String createTransaction(Transaction transaction) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        // Save the transaction
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("Transactions").document(transaction.getId()).set(transaction);

        // Update user details
        User user = firebaseService.getUserDetails(transaction.getUser());
        if (user == null) {
            return "User not found";
        }

        updateUserData(user, transaction);

        firebaseService.updateUserDetails(user);

        return collectionsApiFuture.get().getUpdateTime().toString();
    }

private void updateUserData(User user, Transaction transaction) {
    // Update assets
    String assetType = transaction.getAsset();
    String company = transaction.getCompany();
    double price = transaction.getPrice();
    int quantity = transaction.getQuantity();
    String transactionType = transaction.getType();

    user.getAssets().computeIfAbsent(assetType, k -> new HashMap<>())
        .merge(company, price*quantity, (oldValue, newValue) -> {
            if ("Sell".equalsIgnoreCase(transactionType)) {
                return oldValue - newValue;
            } else {
                return oldValue + newValue;
            }
        });

    // Update portfolio value
    if ("Buy".equalsIgnoreCase(transactionType)) {
        user.setPortfolioValue(user.getPortfolioValue() + (int) (price * quantity));
        user.setTotalInvestedAmount(user.getTotalInvestedAmount() + (int) (price * quantity));
    } else if ("Sell".equalsIgnoreCase(transactionType)) {
        user.setPortfolioValue(user.getPortfolioValue() - (int) (price * quantity));
        user.setTotalInvestedAmount(user.getTotalInvestedAmount() - (int) (price * quantity));
    }

    // Update companies invested
    user.getCompaniesInvested().merge(company, (int) (price * quantity), (oldValue, newValue) -> {
        if ("Sell".equalsIgnoreCase(transactionType)) {
            return oldValue - newValue;
        } else {
            return oldValue + newValue;
        }
    });

    // Add transaction ID to user's transaction history
    user.getTransactionIds().add(transaction.getId());
}

    public Transaction getTransaction(String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        DocumentReference docRef = dbFirestore.collection("Transactions").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            return document.toObject(Transaction.class);
        } else {
            return null;
        }
    }


}
