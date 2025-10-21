package com.codehub.financetracker.repository;

import com.codehub.financetracker.entity.Transaction;
import com.codehub.financetracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);
}
