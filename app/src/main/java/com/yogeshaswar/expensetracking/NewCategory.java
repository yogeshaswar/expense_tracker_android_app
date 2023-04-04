package com.yogeshaswar.expensetracking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yogeshaswar.expensetracking.models.Category;
import com.yogeshaswar.expensetracking.viewmodel.MAViewModel;

public class NewCategory extends AppCompatActivity {
    EditText etCategory, etDescription;
    Button btnAdd;
    MAViewModel maViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category);
        initiateUI();
        clickHandler();

    }

    private void clickHandler() {
        btnAdd.setOnClickListener((v) -> {
            Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
            String categoryName = etCategory.getText().toString();
            String categoryDescription = etDescription.getText().toString();
            if(categoryName.equals("")) {
                return;
            }
            Category newCategory = new Category(categoryName, categoryDescription);
            maViewModel.addNewCategory(newCategory);
            etCategory.setText("");
            etDescription.setText("");
            Intent intent = new Intent(NewCategory.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void initiateUI() {
        etCategory = findViewById(R.id.et_category_name);
        etDescription = findViewById(R.id.et_category_description);
        btnAdd = findViewById(R.id.btn_add);
        maViewModel = new ViewModelProvider(this).get(MAViewModel.class);
    }
}