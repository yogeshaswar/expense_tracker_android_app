package com.yogeshaswar.expensetracking.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.yogeshaswar.expensetracking.models.Category;
import com.yogeshaswar.expensetracking.models.Expense;
import com.yogeshaswar.expensetracking.repositories.CategoryRepository;
import com.yogeshaswar.expensetracking.repositories.ExpenseRepository;

import java.util.ArrayList;
import java.util.List;

public class MAViewModel extends AndroidViewModel {
    private ExpenseRepository expenseRepository;
    private CategoryRepository categoryRepository;
    private List<Expense> expenses = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();
    List<Expense> selectedCategoryExpense = new ArrayList<>();

    public MAViewModel(@NonNull Application application) {
        super(application);
        // expense repository
        expenseRepository = new ExpenseRepository(application);
        // category repository
        categoryRepository = new CategoryRepository(application);
    }

    public void addNewExpense(Expense expense) {
        expenseRepository.addExpense(expense);
    }

    public void deleteExpense(Expense expense) {
        expenseRepository.deleteExpense(expense);
    }

    public List<Expense> getExpenses() {
        expenses = expenseRepository.getExpenses();
        return expenses;
    }

    public List<Expense> getSelectedCategoryExpenses(int categoryID) {
        selectedCategoryExpense = expenseRepository.getSelectedCategoryExpense(categoryID);
        return selectedCategoryExpense;
    }

    // category methods

    public void addNewCategory(Category category) {
        categoryRepository.addCategory(category);
    }

    public void deleteCategory(Category category) {
        categoryRepository.deleteCategory(category);
    }

    public List<Category> getAllCategories() {
        categories = categoryRepository.getCategories();
//        Toast.makeText(getApplication(), "" + categories.size() , Toast.LENGTH_SHORT).show();
        return categories;
    }
}
