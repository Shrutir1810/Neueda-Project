package com.example.portfolio.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.HashMap;
import com.example.portfolio.objects.Transaction;
import com.example.portfolio.service.TransactionService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transactions")
    public ResponseEntity<Map<String, String>> createTransaction(@RequestBody Transaction transaction) throws InterruptedException, ExecutionException {
        String result = transactionService.createTransaction(transaction);
        Map<String, String> response = new HashMap<>();
        response.put("message", result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/transactions/{id}")
public ResponseEntity<Transaction> getTransaction(@PathVariable String id) throws InterruptedException, ExecutionException {
    Transaction transaction = transactionService.getTransaction(id);
    if (transaction != null) {
        return ResponseEntity.ok(transaction);
    } else {
        return ResponseEntity.notFound().build();
    }
}

}
