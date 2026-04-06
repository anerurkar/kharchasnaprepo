package com.example.kharcha.repository;

import com.example.kharcha.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUserIdAndDate(String userId, LocalDate date);
	
}