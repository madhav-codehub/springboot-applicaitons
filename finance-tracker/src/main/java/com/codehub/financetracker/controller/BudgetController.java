package com.codehub.financetracker.controller;

import com.codehub.financetracker.entity.Budget;
import com.codehub.financetracker.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/budgets")
public class BudgetController {

    private final BudgetService budgetService;

    @PostMapping("/addBudget")
    public ResponseEntity<Budget> addBudget(@RequestBody Budget budget, Authentication authentication) {
        return new ResponseEntity<>(budgetService.save(budget, authentication.getName()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Budget>> getAllBudgets(Authentication authentication) {
        return ResponseEntity.ok(budgetService.findByUsername(authentication.getName()));
    }

    @PutMapping("/updateBudget/{id}")
    public ResponseEntity<Budget> updateBudget(@PathVariable Long id, @RequestBody Budget budget, Authentication authentication) {
        return ResponseEntity.ok(budgetService.update(id, budget, authentication.getName()));
    }

    @DeleteMapping("/deleteBudget/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long id, Authentication authentication) {
        budgetService.delete(id, authentication.getName());
        return ResponseEntity.noContent().build();
    }

}
