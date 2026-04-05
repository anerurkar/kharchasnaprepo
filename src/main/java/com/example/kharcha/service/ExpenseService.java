package com.example.kharcha.service;

import com.example.kharcha.model.Expense;
import com.example.kharcha.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ExpenseService {

    private final ExpenseRepository repo;

    public ExpenseService(ExpenseRepository repo) {
        this.repo = repo;
    }

    // ✅ SAVE DATA
    public void addExpenses(String user, List<Expense> expenses) {

        List<Expense> toSave = new ArrayList<>();

        for (Expense e : expenses) {
            toSave.add(new Expense(
                    user,
                    e.getItem(),
                    e.getQty(),
                    e.getAmount(),
                    e.getCategory()
            ));
        }

        repo.saveAll(toSave);
    }

    // ✅ GET TODAY BILL
    public List<Expense> getTodayExpenses(String user) {
        return repo.findByUserAndDate(user, LocalDate.now());
    }
}