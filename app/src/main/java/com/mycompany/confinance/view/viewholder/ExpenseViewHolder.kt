package com.mycompany.confinance.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.mycompany.confinance.databinding.RowExpenseBinding
import com.mycompany.confinance.model.movement.GetMovementModel

class ExpenseViewHolder(private val bind: RowExpenseBinding): RecyclerView.ViewHolder(bind.root){

    fun bind(movement: GetMovementModel){
        bind.textInformation.text = "${movement.description} - ${movement.date}"
        bind.textValue.text = "$ ${movement.value}"
    }
}