package com.mycompany.confinance.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.confinance.databinding.RowRecyclerBinding
import com.mycompany.confinance.model.movement.GetMovementModel
import com.mycompany.confinance.view.OnMovementListener
import com.mycompany.confinance.view.viewholder.ExpenseViewHolder

class ExpenseAdapter:  RecyclerView.Adapter<ExpenseViewHolder>() {
    private var listExpense: List<GetMovementModel> = listOf()
    private lateinit var listener: OnMovementListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val item = RowRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpenseViewHolder(item,listener)
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

    fun movementClick(movement: OnMovementListener){
        listener = movement
    }
}