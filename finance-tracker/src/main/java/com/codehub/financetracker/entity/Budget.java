package com.codehub.financetracker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tbl_budget")
@Data
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;

    private double amount;

    private String monthYear; // e.g., "2025-10"

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
