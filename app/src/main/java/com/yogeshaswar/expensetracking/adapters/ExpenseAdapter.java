package com.yogeshaswar.expensetracking.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yogeshaswar.expensetracking.R;
import com.yogeshaswar.expensetracking.models.Expense;

import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {
    List<Expense> expenses;

    public ExpenseAdapter(List<Expense> expenses) {
        this.expenses = expenses;
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.expense_card,
                parent,
                false
        );
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        if(expenses.size() == 0) {
//            holder.title.setText("Expenses will be shown here");
        } else {
            //        holder.title.setText(expenses.get(position).getExpenseTitle());
            holder.amount.setText(String.valueOf(expenses.get(position).getExpenseAmount()));

            holder.title.setText(expenses.get(position).getExpenseTitle());
        }
        // TODO: add data and time and show on expense card

    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder {
        TextView title, amount, description;
        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            amount = (TextView) itemView.findViewById(R.id.tv_amount);
        }
    }

}
