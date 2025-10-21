package com.codehub.financetracker.controller;

import com.codehub.financetracker.entity.Transaction;
import com.codehub.financetracker.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/addTransaction")
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction, Authentication authentication) {
        return new ResponseEntity<>(transactionService.save(transaction, authentication.getName()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getTransactions(Authentication authentication) {
        return ResponseEntity.ok(transactionService.findByUsername(authentication.getName()));
    }

    @PutMapping("/updateTransaction/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction, Authentication authentication) {
        return ResponseEntity.ok(transactionService.update(id, transaction, authentication.getName()));
    }

    @DeleteMapping("/deleteTransaction/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id, Authentication authentication) {
        transactionService.delete(id, authentication.getName());
        return ResponseEntity.noContent().build();
    }

}
