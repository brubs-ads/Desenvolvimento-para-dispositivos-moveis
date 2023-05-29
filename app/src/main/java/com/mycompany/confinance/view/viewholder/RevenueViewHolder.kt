package com.mycompany.confinance.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.mycompany.confinance.databinding.RowRevenueBinding
import com.mycompany.confinance.model.movement.GetMovementModel

class RevenueViewHolder(private val bind: RowRevenueBinding, private val onLongClickListener: (GetMovementModel) -> Unit
) : RecyclerView.ViewHolder(bind.root) {

    fun bind(movement: GetMovementModel) {
        bind.textInformation.text = "${movement.description} - ${movement.date}"
        bind.textValue.text = "$ ${movement.value}"

        bind.root.setOnLongClickListener {
            onLongClickListener(movement)
            true
        }
    }
}