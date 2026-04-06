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

public List<Expense> getAllToday(String user) {
    return repo.findByUserIdAndDate(user, LocalDate.now());
}
    public void addExpenses(String userId, List<Expense> expenses) {

        List<Expense> toSave = new ArrayList<>();

        for (Expense e : expenses) {
            toSave.add(new Expense(
                    userId,
                    e.getItem(),
                    e.getQty(),
                    e.getAmount(),
                    e.getCategory()
            ));
        }

        repo.saveAll(toSave);
    }

    public List<Expense> getTodayExpenses(String userId) {
        return repo.findByUserIdAndDate(userId, LocalDate.now());
    }

    /* public String generateInsights(String userId) {

        List<Expense> expenses = getTodayExpenses(userId);

        if (expenses.isEmpty()) {
            return "No data available for insights today";
        }

        double total = expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        Map<String, Double> categorySpend = new HashMap<>();
        for (Expense e : expenses) {
            categorySpend.put(
                    e.getCategory(),
                    categorySpend.getOrDefault(e.getCategory(), 0.0) + e.getAmount()
            );
        }

        String topCategory = Collections.max(categorySpend.entrySet(),
                Map.Entry.comparingByValue()).getKey();

        return "📊 Insights\n\n" +
                "💰 Total: ₹" + (int) total + "\n" +
                "🔥 Top Category: " + topCategory;
    } */
	public String generateInsights(String user) {

    List<Expense> expenses = getAllToday(user);

    if (expenses.isEmpty()) {
        return "No data available for insights today";
    }

    double total = expenses.stream()
            .mapToDouble(Expense::getAmount)
            .sum();

    // 🔥 Category-wise total
    Map<String, Double> categorySpend = new HashMap<>();
    for (Expense e : expenses) {
        categorySpend.put(
                e.getCategory(),
                categorySpend.getOrDefault(e.getCategory(), 0.0) + e.getAmount()
        );
    }

    String topCategory = Collections.max(categorySpend.entrySet(),
            Map.Entry.comparingByValue()).getKey();

    double topCategoryAmount = categorySpend.get(topCategory);

    // 📦 Most frequent item
    Map<String, Integer> itemCount = new HashMap<>();
    for (Expense e : expenses) {
        itemCount.put(
                e.getItem(),
                itemCount.getOrDefault(e.getItem(), 0) + 1
        );
    }

    String topItem = Collections.max(itemCount.entrySet(),
            Map.Entry.comparingByValue()).getKey();

    int topItemCount = itemCount.get(topItem);

    // ⚠️ Highest expense item
    Expense maxExpense = Collections.max(expenses,
            Comparator.comparingDouble(Expense::getAmount));

    // 💡 Simple AI-style suggestion
    String suggestion = "You are spending more on " + topCategory +
            ". Consider optimizing or reducing this category.";

    return "📊 *Spending Insights*\n\n" +
            "💰 Total Spend Today: ₹" + (int) total + "\n" +
            "🔥 Top Category: " + topCategory + " (₹" + (int) topCategoryAmount + ")\n" +
            "📦 Most Bought Item: " + topItem + " (" + topItemCount + " times)\n" +
            "⚠️ High Expense Item: " + maxExpense.getItem() + " ₹" + (int) maxExpense.getAmount() + "\n\n" +
            "💡 Suggestion:\n" + suggestion;
}

}