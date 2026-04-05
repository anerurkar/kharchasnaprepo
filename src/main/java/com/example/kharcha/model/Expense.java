package com.example.kharcha.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String user;
    private String item;
    private double qty;
    private double amount;
    private String category;
    private LocalDate date;

    // ✅ Required for JPA
public Expense() {}

// ✅ Used by ParserUtil
public Expense(String item, double qty, double amount, String category) {
    this.item = item;
    this.qty = qty;
    this.amount = amount;
    this.category = category;
    this.date = LocalDate.now();
}

// ✅ Used by DB save
public Expense(String user, String item, double qty, double amount, String category) {
    this.user = user;
    this.item = item;
    this.qty = qty;
    this.amount = amount;
    this.category = category;
    this.date = LocalDate.now();
}

    public String getUser() { return user; }
    public String getItem() { return item; }
    public double getQty() { return qty; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public LocalDate getDate() { return date; }
}