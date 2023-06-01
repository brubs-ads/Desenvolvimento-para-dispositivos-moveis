package com.mycompany.confinance.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.mycompany.confinance.databinding.RowRecyclerBinding
import com.mycompany.confinance.model.movement.GetMovementModel

class ExpenseViewHolder(private val bind: RowRecyclerBinding): RecyclerView.ViewHolder(bind.root){

    fun bind(movement: GetMovementModel){
        bind.textInformation.text = "${movement.description} - ${movement.date}"
        bind.textValue.text = "$ ${movement.value}"
    }
}