package com.yogeshaswar.expensetracking.repositories;

import android.app.Application;
import android.widget.Toast;

import com.yogeshaswar.expensetracking.database.AppDatabase;
import com.yogeshaswar.expensetracking.database.CategoryDAO;
import com.yogeshaswar.expensetracking.models.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    Application application;
    CategoryDAO categoryDAO;
    List<Category> categories = new ArrayList<>();
    public CategoryRepository(Application application) {
        this.application = application;
        //methods -> dao -> database
        AppDatabase database = AppDatabase.getInstance(application);
        categoryDAO = database.categoryDAO();
    }
    // methods
    public void addCategory(Category category) {
        categoryDAO.insert(category);
    }

    public void deleteCategory(Category category) {
        categoryDAO.delete(category);
    }

    public List<Category> getCategories() {
        categories = categoryDAO.getCategories();
//        Toast.makeText(application, "" + (categories.size()), Toast.LENGTH_SHORT).show();
        return categories;
    }
}
