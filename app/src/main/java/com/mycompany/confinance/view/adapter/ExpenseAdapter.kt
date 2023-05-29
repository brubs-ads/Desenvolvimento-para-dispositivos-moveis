package com.mycompany.confinance.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.confinance.databinding.RowExpenseBinding
import com.mycompany.confinance.model.movement.GetMovementModel
import com.mycompany.confinance.view.viewholder.ExpenseViewHolder

class ExpenseAdapter :  RecyclerView.Adapter<ExpenseViewHolder>() {
    private var listExpense: List<GetMovementModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val item = RowExpenseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpenseViewHolder(item)
    }

    override fun getItemCount(): Int {
        return listExpense.size
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.bind(listExpense[position])
    }

    fun updateExpense(list: List<GetMovementModel>) {
        listExpense = list
    }
}