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

    @PostMapping(produces = "application/xml")   // ✅ IMPORTANT
    public String receiveMessage(@RequestParam Map<String, String> params) {

        String message = params.get("Body");
        String from = params.get("From");

        // Normalize user
        if (from != null) {
            from = from.replace("whatsapp:", "").replace("+", "").trim();
        }

        if (message == null || message.trim().isEmpty()) {
            return buildTwilioResponse("Invalid message");
        }

        message = message.trim().toLowerCase();

        // ✅ BILL
        if (message.contains("bill")) {
            return generateTodayBill(from);
        }

        // ✅ INSIGHTS
        if (message.equals("insights")) {
            return buildTwilioResponse(service.generateInsights(from));
        }

        // ✅ NORMAL FLOW
        List<Expense> expenses = ParserUtil.parseMessage(message);

        if (expenses.isEmpty()) {
            return buildTwilioResponse("Invalid format. Example: milk 2 60");
        }

        service.addExpenses(from, expenses);

        double total = expenses.stream()
                .mapToDouble(Expense::getAmount)
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

        return buildTwilioResponse(response.toString());   // ✅ FIXED
    }

    // ✅ BILL METHOD
    private String generateTodayBill(String user) {

        List<Expense> expenses = service.getTodayExpenses(user);

        if (expenses.isEmpty()) {
            return buildTwilioResponse("No of expenses recorded today");
        }

        double total = expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        StringBuilder response = new StringBuilder("🧾 Today You have spent:\n");

        response.append("```")
                .append(String.format("%-5s %-12s %-10s %-20s %-15s %-10s\n",
                    "SrNo", "Date", "Qty", "Item Name", "Category", "Amount"))

                .append("------------------------------------------------------------\n");
int index = 1;
        for (Expense e : expenses) {
            response.append(String.format("%-5d %-12s %-10d %-20s %-15s ₹%-10d\n",
                index++,                                // ✅ Serial No
                e.getDate(),                            // ✅ Date
                (int) e.getQty(),
                e.getItem(),
                e.getCategory(),
                (int) e.getAmount()
        ));

        }

        response.append("------------------------------------------------------------\n");
        response.append(String.format("%-5s %-12s %-10s %-20s %-15s ₹%-10d\n",
            "",
            "",
            "",
            "TOTAL",
            "",
            (int) total
    ));


        response.append("```");

        return buildTwilioResponse(response.toString());
    }

    // ✅ TWILIO FORMAT (CRITICAL)
    private String buildTwilioResponse(String message) {
        return "<Response><Message>" + message + "</Message></Response>";
    }
}