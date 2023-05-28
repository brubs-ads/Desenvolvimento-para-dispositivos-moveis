package com.mycompany.confinance.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.mycompany.confinance.databinding.RowRevenueBinding
import com.mycompany.confinance.model.movement.GetMovementModel

class RevenueViewHolder(private val bind: RowRevenueBinding): RecyclerView.ViewHolder(bind.root) {

    fun bind(movement: GetMovementModel){
        bind.textInformation.text = "${movement.description} - ${movement.date}"
        bind.textValue.text = "$ ${movement.value}"
    }
}