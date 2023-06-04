package com.yogeshaswar.expensetracking.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.yogeshaswar.expensetracking.models.Category;
import com.yogeshaswar.expensetracking.models.Expense;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Category.class, Expense.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "expense.db";

    // abstract dao methods
    public abstract CategoryDAO categoryDAO();
    public abstract ExpenseDAO expenseDAO();
    public abstract NoteDAO noteDAO();

    // database instance
    private static AppDatabase INSTANCE;
    // method to get INSTANCE
    public static synchronized AppDatabase getInstance(Context context) {
        // create if null
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
    // Callback
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // Insert data when db is created...
            InitializeData();
        }
    };

    private static void InitializeData() {
        CategoryDAO categoryDAO = INSTANCE.categoryDAO();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                //Categories
                Category categories = new Category();
                categories.setCategoryName("Categories");
                categories.setCategoryDescription("Rent, Food, Electricity, Bills, etc.");
                categories.setCategoryId(0);

                categoryDAO.insert(categories);
            }
        });
    }
}

