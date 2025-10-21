package com.codehub.financetracker.service;

import com.codehub.financetracker.entity.Transaction;
import com.codehub.financetracker.entity.User;
import com.codehub.financetracker.exception.ResourceNotFoundException;
import com.codehub.financetracker.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserService userService;

    public Transaction save(Transaction transaction, String username) {
        User user = userService.findByUsername(username);
        transaction.setUser(user);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> findByUsername(String username) {
        User user = userService.findByUsername(username);
        return transactionRepository.findByUser(user);
    }

    public Transaction update(Long id, Transaction updatedTransaction, String username) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Transaction not found!")
        );
        if (!transaction.getUser().getUsername().equals(username)) {
            throw new ResourceNotFoundException("Unauthorized!");
        }
        transaction.setAmount(updatedTransaction.getAmount());
        transaction.setCategory(updatedTransaction.getCategory());
        transaction.setDate(updatedTransaction.getDate());
        transaction.setDescription(updatedTransaction.getDescription());
        return transactionRepository.save(transaction);
    }

    public void delete(Long id, String username) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Transaction not found!")
        );
        if (!transaction.getUser().getUsername().equals(username)) {
            throw new ResourceNotFoundException("Unauthorized!");
        }
        transactionRepository.deleteById(id);
    }
}
