package com.yogeshaswar.expensetracking.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.yogeshaswar.expensetracking.models.Expense;

import java.util.List;

@Dao
public interface ExpenseDAO {

    @Insert
    void insert(Expense expense);

    @Delete
    void delete(Expense expense);

    @Query("SELECT * FROM expense_table")
    List<Expense> getExpenses();

    @Query("SELECT * FROM expense_table WHERE linked_category_id=:categoryID")
    List<Expense> getSelectedCategoryExpense(int categoryID);
}
