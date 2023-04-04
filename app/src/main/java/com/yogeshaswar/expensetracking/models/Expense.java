package com.yogeshaswar.expensetracking.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "expense_table", foreignKeys = @ForeignKey(entity = Category.class,
        parentColumns = "categoryId", childColumns = "linked_category_id",
        onDelete = CASCADE))
public class Expense {
    @PrimaryKey(autoGenerate = true)
    int expenseId;
    @ColumnInfo(name = "expense_title")
    String expenseTitle;
    @ColumnInfo(name = "expense_amount")
    int expenseAmount;
    @ColumnInfo(name = "expense_description")
    String expenseDescription;
    @ColumnInfo(name = "linked_category_id")
    int linkedCategoryId;
    @Ignore
    public Expense() {
    }
    @Ignore
    public Expense(String expenseTitle, int expenseAmount, String expenseDescription, int linkedCategoryId) {
        this.expenseTitle = expenseTitle;
        this.expenseAmount = expenseAmount;
        this.expenseDescription = expenseDescription;
        this.linkedCategoryId = linkedCategoryId;
    }

    public Expense(int expenseId, String expenseTitle, int expenseAmount, String expenseDescription, int linkedCategoryId) {
        this.expenseId = expenseId;
        this.expenseTitle = expenseTitle;
        this.expenseAmount = expenseAmount;
        this.expenseDescription = expenseDescription;
        this.linkedCategoryId = linkedCategoryId;
    }
    @Ignore
    public Expense(String expenseTitle, int expenseAmount, String expenseDescription) {
        this.expenseTitle = expenseTitle;
        this.expenseAmount = expenseAmount;
        this.expenseDescription = expenseDescription;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public String getExpenseTitle() {
        return expenseTitle;
    }

    public void setExpenseTitle(String expenseTitle) {
        this.expenseTitle = expenseTitle;
    }

    public int getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(int expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public String getExpenseDescription() {
        return expenseDescription;
    }

    public void setExpenseDescription(String expenseDescription) {
        this.expenseDescription = expenseDescription;
    }

    public int getLinkedCategoryId() {
        return linkedCategoryId;
    }

    public void setLinkedCategoryId(int linkedCategoryId) {
        this.linkedCategoryId = linkedCategoryId;
    }
}
