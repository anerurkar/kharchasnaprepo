package com.example.kharcha.controller;

import com.example.kharcha.model.Expense;
import com.example.kharcha.service.ExpenseService;
import com.example.kharcha.util.ParserUtil;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/whatsapp")
public class WhatsAppController {

    private final ExpenseService service;

    public WhatsAppController(ExpenseService service) {
        this.service = service;
    }

    @PostMapping
    public String receiveMessage(@RequestParam Map<String, String> params) {

        String message = params.get("Body");
        String from = params.get("From");

        // ✅ Normalize user (VERY IMPORTANT)
        if (from != null) {
            from = from.replace("whatsapp:", "").trim();
        }

        if (message == null || message.trim().isEmpty()) {
            return "";
        }

        message = message.trim().toLowerCase();

        // ✅ BILL FEATURE
        if (message.equals("bill")) {
            return generateTodayBill(from);
        }

        // ✅ NORMAL FLOW
        List<Expense> expenses = ParserUtil.parseMessage(message);

        service.addExpenses(from, expenses);

        double total = expenses.stream()
                .mapToDouble(e -> e.getAmount())
                .sum();

        StringBuilder response = new StringBuilder("✅ Added:\n");

        response.append("```")
                .append(String.format("%-10s %-20s %-15s %-10s\n",
                        "Quantity", "Item Name", "Category", "Amount"))
                .append("------------------------------------------------------------\n");

        for (Expense e : expenses) {
            response.append(String.format("%-10d %-20s %-15s ₹%-10d\n",
                    (int) e.getQty(),
                    e.getItem(),
                    e.getCategory(),
                    (int) e.getAmount()
            ));
        }

        response.append("------------------------------------------------------------\n");
        response.append(String.format("%-10s %-20s %-15s ₹%-10d\n",
                "",
                "TOTAL",
                "",
                (int) total
        ));

        response.append("```");

        return response.toString();
    }

    // ✅ BILL METHOD
    private String generateTodayBill(String user) {

        List<Expense> expenses = service.getTodayExpenses(user);

        if (expenses.isEmpty()) {
            return "No expenses recorded today";
        }

        double total = expenses.stream()
                .mapToDouble(e -> e.getAmount())
                .sum();

        StringBuilder response = new StringBuilder("🧾 *Today You have spent *\n");

        response.append("```")
                .append(String.format("%-10s %-20s %-15s %-10s\n",
                        "Quantity", "Item Name", "Category", "Amount"))
                .append("------------------------------------------------------------\n");

        for (Expense e : expenses) {
            response.append(String.format("%-10d %-20s %-15s ₹%-10d\n",
                    (int) e.getQty(),
                    e.getItem(),
                    e.getCategory(),
                    (int) e.getAmount()
            ));
        }

        response.append("------------------------------------------------------------\n");
        response.append(String.format("%-10s %-20s %-15s ₹%-10d\n",
                "",
                "TOTAL",
                "",
                (int) total
        ));

        response.append("```");

        return response.toString();
    }
}