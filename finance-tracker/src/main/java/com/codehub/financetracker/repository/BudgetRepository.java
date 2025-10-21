package com.codehub.financetracker.repository;

import com.codehub.financetracker.entity.Budget;
import com.codehub.financetracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByUser(User user);
}
