package com.yogeshaswar.expensetracking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.yogeshaswar.expensetracking.models.Category;
import com.yogeshaswar.expensetracking.models.Expense;
import com.yogeshaswar.expensetracking.viewmodel.MAViewModel;

import java.util.ArrayList;
import java.util.List;

public class NewExpense extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText etExpenseTitle, etExpenseAmt, etDescription;
    Button btnAddExpense;
    Spinner spinnerCategory;
    MAViewModel maViewModel;
    List<String> categoriesList = new ArrayList<>();
    private int selectedCategoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expense);
        initiateUI();
        populateCategoriesList();
        loadSpinner(); // with available categories
        clickHandler();
        spinnerCategory.setOnItemSelectedListener(this);

    }

    private void populateCategoriesList() {
        maViewModel = new ViewModelProvider(this).get(MAViewModel.class);
        List<Category> allCategories = maViewModel.getAllCategories();

        for (int i = 0; i <allCategories.size() ; i++) {
            categoriesList.add(allCategories.get(i).getCategoryName());
        }
    }

    private void clickHandler() {
        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etExpenseTitle.getText().toString();
                int amt;
                String description = etDescription.getText().toString();
                Expense newExpense = new Expense();
                if(!(etExpenseAmt.getText().toString()).equals("")) {
                    amt = Integer.parseInt(etExpenseAmt.getText().toString());
                    // Expense object
                    if(selectedCategoryId == 0) {
                        Toast.makeText(NewExpense.this, "Please Select one category", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        newExpense = new Expense(title, amt, description, selectedCategoryId);
                    }

                }

                if(title.equals("")) {
                    Toast.makeText(NewExpense.this, "error saving", Toast.LENGTH_SHORT).show();
                    return;
                }
                maViewModel.addNewExpense(newExpense);
                // set to default values
                etExpenseTitle.setText("");
                etExpenseAmt.setText("0");
                etDescription.setText("");
                Intent intent = new Intent(NewExpense.this, ShowExpense.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadSpinner() {
        ArrayAdapter adapter = new ArrayAdapter(
                getApplicationContext(),android.R.layout.simple_list_item_1 ,categoriesList);

        spinnerCategory.setAdapter(adapter);
    }

    private void initiateUI() {
        spinnerCategory = (Spinner) findViewById(R.id.spinner_category);
        etExpenseTitle = findViewById(R.id.et_expense_title);
        etExpenseAmt = findViewById(R.id.et_expense_amt);
        etDescription = findViewById(R.id.et_expense_description);
        btnAddExpense = findViewById(R.id.btn_add);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedCategoryId = (int) id;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        selectedCategoryId = 0;
    }
}