package com.codehub.financetracker.service;

import com.codehub.financetracker.entity.Budget;
import com.codehub.financetracker.entity.User;
import com.codehub.financetracker.exception.ResourceNotFoundException;
import com.codehub.financetracker.repository.BudgetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetService {
    private final BudgetRepository budgetRepository;
    private final UserService userService;

    public Budget save(Budget budget, String username) {
        User user = userService.findByUsername(username);
        budget.setUser(user);
        return budgetRepository.save(budget);
    }

    public List<Budget> findByUsername(String username) {
        User user = userService.findByUsername(username);
        return budgetRepository.findByUser(user);
    }

    public Budget update(Long id, Budget updatedBudget, String username) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Budget not found"));
        if (!budget.getUser().getUsername().equals(username)) {
            throw new ResourceNotFoundException("Unauthorized");
        }
        budget.setCategory(updatedBudget.getCategory());
        budget.setAmount(updatedBudget.getAmount());
        budget.setMonthYear(updatedBudget.getMonthYear());
        return budgetRepository.save(budget);
    }

    public void delete(Long id, String username) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Budget not found"));
        if (!budget.getUser().getUsername().equals(username)) {
            throw new ResourceNotFoundException("Unauthorized");
        }
        budgetRepository.deleteById(id);
    }
}
