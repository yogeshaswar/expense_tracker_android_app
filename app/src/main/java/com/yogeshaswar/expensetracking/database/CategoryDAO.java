package com.yogeshaswar.expensetracking.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.yogeshaswar.expensetracking.models.Category;

import java.util.List;

@Dao
public interface CategoryDAO {
    // methods
    @Insert
    void insert(Category category);

    @Delete
    void delete(Category category);

    @Query("SELECT * FROM category_table")
    List<Category> getCategories();
}
