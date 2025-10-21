package com.codehub.financetracker.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_transaction")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;

    private String category;

    private LocalDate date;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
