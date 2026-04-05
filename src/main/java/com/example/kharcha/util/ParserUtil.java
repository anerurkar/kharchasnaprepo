package com.example.kharcha.util;

import com.example.kharcha.model.Expense;
import java.util.*;
import java.util.regex.*;

public class ParserUtil {

    public static List<Expense> parseMessage(String message) {
        List<Expense> expenses = new ArrayList<>();
        String[] parts = message.split(",");

        for (String part : parts) {
            part = part.trim();

            List<Double> numbers = new ArrayList<>();
            Matcher matcher = Pattern.compile("\\d+(\\.\\d+)?").matcher(part);

            while (matcher.find()) {
                numbers.add(Double.parseDouble(matcher.group()));
            }

            double amount = 0;
            double qty = 1;

            if (numbers.size() == 1) {
                amount = numbers.get(0);
            } else if (numbers.size() >= 2) {
                qty = numbers.get(0);                      // first number = quantity
                amount = numbers.get(numbers.size() - 1);  // last = total amount
            }

            String item = part.replaceAll("\\d+(\\.\\d+)?", "").trim();
            item = item.replaceAll("\\s{2,}", " ");

            String category = detectCategory(item);

            expenses.add(new Expense(item, qty, amount, category));
        }

        return expenses;
    }

    private static String detectCategory(String item) {

    item = item.toLowerCase();

    // 🥛 Dairy
    if (item.contains("milk") || item.contains("paneer") || item.contains("ghee") || item.contains("curd")) {
        return "🥛 Dairy";
    }

    // 🍔 Food / Snacks
    if (item.contains("vada") || item.contains("pav") || item.contains("samosa") ||
        item.contains("tikka") || item.contains("ladu") || item.contains("laddu") ||
        item.contains("food")) {
        return "🍔 Food";
    }

    // 🥦 Grocery / Household
    if (item.contains("kg") || item.contains("rice") || item.contains("wheat") ||
        item.contains("atta") || item.contains("dal") || item.contains("oil") ||
        item.contains("coconut") || item.contains("vegetable") || item.contains("fruit")) {
        return "🥦 Grocery";
    }

    // ⛽ Fuel
    if (item.contains("petrol") || item.contains("diesel")) {
        return "⛽ Fuel";
    }

    return "🛒 General";
}
}