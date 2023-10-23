package com.mycompany.confinance.view.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.mycompany.confinance.databinding.RowMovementBinding
import com.mycompany.confinance.model.MovementModel

class MovementViewHolder(private val item: RowMovementBinding) : RecyclerView.ViewHolder(item.root) {
    fun bind(movementModel: MovementModel) {
        item.textValueMovement.text = "R$ ${movementModel.value}"
        item.textDate.text = movementModel.date
    }
}