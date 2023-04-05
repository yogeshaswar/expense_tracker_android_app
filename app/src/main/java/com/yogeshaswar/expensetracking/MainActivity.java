package com.yogeshaswar.expensetracking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.yogeshaswar.expensetracking.models.Category;
import com.yogeshaswar.expensetracking.models.Expense;
import com.yogeshaswar.expensetracking.viewmodel.MAViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageView addExpense, addCategory, showExpense, manageMoney, addNote;
    private MAViewModel maViewModel;
    Spinner spinnerCategory;
    List<String> categoriesList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initiateUI();
        clickHandler();
//        populateCategoryList();
        maViewModel = new ViewModelProvider(this).get(MAViewModel.class);
        List<Category> allCategories = maViewModel.getAllCategories();
        for (int i = 0; i <allCategories.size() ; i++) {
            categoriesList.add(allCategories.get(i).getCategoryName());
        }
        loadSpinner(categoriesList);
        



    }

    private void clickHandler() {
        addExpense.setOnClickListener((v) -> {
            Intent intent = new Intent(MainActivity.this, NewExpense.class);
            startActivity(intent);
        });
        addCategory.setOnClickListener((v) -> {
            Intent intent = new Intent(MainActivity.this, NewCategory.class);
            startActivity(intent);
        });
        showExpense.setOnClickListener((v) -> {
            Intent intent = new Intent(MainActivity.this, ShowExpense.class);
            startActivity(intent);
        });
        manageMoney.setOnClickListener((v) -> {
            Intent intent = new Intent(MainActivity.this, ManageMoney.class);
            startActivity(intent);
        });
        addNote.setOnClickListener((v) -> {
            Intent intent = new Intent(MainActivity.this, Note.class);
            startActivity(intent);
        });
    }

    private void SpinnerClicksHandler() {
        spinnerCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, "clicked: " + position + "and id: " + id , Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void populateCategoryList() {
        List<Category> categories = maViewModel.getAllCategories();
        for (int i = 0; i < categories.size() ; i++) {
            categoriesList.add(categories.get(i).getCategoryName());
        }
    }

    private void loadSpinner(List<String> categories) {

        ArrayAdapter<String> adapter = new ArrayAdapter(
                getApplicationContext(),android.R.layout.simple_list_item_1 ,categories);

        spinnerCategory.setAdapter(adapter);
    }


    private void initiateUI() {
        spinnerCategory = (Spinner) findViewById(R.id.spinner_category);
        addExpense = (ImageView) findViewById(R.id.add_new_expense);
        addCategory = (ImageView) findViewById(R.id.add_new_category);
        showExpense = (ImageView) findViewById(R.id.show_all_expenses);
        manageMoney = (ImageView) findViewById(R.id.manage_money);
        addNote = (ImageView) findViewById(R.id.add_note);
    }

    private List<Expense> getAllExpensesFromDB() {
        return maViewModel.getExpenses();
    }
}