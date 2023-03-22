package com.pairlearning.expensetracker.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {
    private Integer userId;
    private String title;
    private String description;
    private Double totalExpense;
    private Integer categoryId;

    public Category(int categoryId, int userId, String title, String description, double totalExpense) {
    }
}
