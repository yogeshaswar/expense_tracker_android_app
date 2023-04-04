package com.yogeshaswar.expensetracking.repositories;

import android.app.Application;

import com.yogeshaswar.expensetracking.database.AppDatabase;
import com.yogeshaswar.expensetracking.database.ExpenseDAO;
import com.yogeshaswar.expensetracking.models.Category;
import com.yogeshaswar.expensetracking.models.Expense;

import java.util.ArrayList;
import java.util.List;

public class ExpenseRepository {
    Application application;
    ExpenseDAO expenseDAO;
    List<Expense> expenses = new ArrayList<Expense>();
    List<Expense> selectedCategoryExpense = new ArrayList<>();

    public ExpenseRepository(Application application) {
        this.application = application;
        AppDatabase database = AppDatabase.getInstance(application);
        expenseDAO = database.expenseDAO();
    }
    // methods
    public void addExpense(Expense expense) {
        expenseDAO.insert(expense);
    }

    public void deleteExpense(Expense expense) {
        expenseDAO.delete(expense);
    }

    public List<Expense> getExpenses() {
        expenses = expenseDAO.getExpenses();
        return expenses;
    }
    // get category wise data
    public List<Expense> getSelectedCategoryExpense(int categoryID) {
        selectedCategoryExpense = expenseDAO.getSelectedCategoryExpense(categoryID);
        return selectedCategoryExpense;
    }
}
