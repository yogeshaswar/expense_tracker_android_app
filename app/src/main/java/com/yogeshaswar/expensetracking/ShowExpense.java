package com.yogeshaswar.expensetracking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.yogeshaswar.expensetracking.adapters.ExpenseAdapter;
import com.yogeshaswar.expensetracking.models.Category;
import com.yogeshaswar.expensetracking.models.Expense;
import com.yogeshaswar.expensetracking.viewmodel.MAViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShowExpense extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    RecyclerView recyclerView;
    List<Expense> expenseList = new ArrayList<>();
    MAViewModel maViewModel;
    Spinner categorySpinner;
    List<String> categoryList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_expense);
        initiateUI();
        expenseList = getExpenses();
        loadRecyclerView(expenseList);
        loadCategorySpinner();
        categorySpinner.setOnItemSelectedListener(this);

        // TODO: delete on swipe expense and category with alert message
    }

    private void loadCategorySpinner() {
        // get category list
        List<Category> allCategories = maViewModel.getAllCategories();
        for (int i = 0; i < allCategories.size() ; i++) {
            categoryList.add(allCategories.get(i).getCategoryName());
        }
        ArrayAdapter<Category> adapter = new ArrayAdapter(
                getApplicationContext(),android.R.layout.simple_list_item_1 ,categoryList);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        categorySpinner.setAdapter(adapter);
    }

    private List<Expense> getExpenses() {
        return maViewModel.getExpenses();
    }

    private void loadRecyclerView(List<Expense> expenses) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // adapter
        ExpenseAdapter adapter = new ExpenseAdapter(expenses);
        recyclerView.setAdapter(adapter);
    }

    private void initiateUI() {
        recyclerView = findViewById(R.id.recycler_view);
        maViewModel = new ViewModelProvider(this).get(MAViewModel.class);
        categorySpinner = (Spinner) findViewById(R.id.spinner_category);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        List<Expense> selectedCategoryExpenses = maViewModel.getSelectedCategoryExpenses((int) id);
        loadRecyclerView(selectedCategoryExpenses);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // default
//        expenseList = getExpenses();
//        loadRecyclerView(expenseList);
    }
}