package com.mycompany.confinance.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.mycompany.confinance.databinding.RowRevenueBinding
import com.mycompany.confinance.model.movement.GetMovementModel
import com.mycompany.confinance.view.OnMovementListener

class RevenueViewHolder(private val bind: RowRevenueBinding, private val listener : OnMovementListener
) : RecyclerView.ViewHolder(bind.root) {

    fun bind(movement: GetMovementModel) {
        bind.textInformation.text = "${movement.description} - ${movement.date}"
        bind.textValue.text = "$ ${movement.value}"

        bind.viewRecycler.setOnClickListener {
            listener.onClick(movement.id!!)
        }
        bind.viewRecycler.setOnLongClickListener {
            listener.onDelete(movement.id!!)
            true
        }
        }
    }