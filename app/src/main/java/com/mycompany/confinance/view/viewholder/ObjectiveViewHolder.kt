package com.mycompany.confinance.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.mycompany.confinance.databinding.RowRecyclerBinding
import com.mycompany.confinance.model.objective.ObjectiveModel
import com.mycompany.confinance.view.OnMovementListener

class ObjectiveViewHolder(
    private val binding: RowRecyclerBinding,
    private val listener: OnMovementListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(objective: ObjectiveModel) {
        binding.textValue.text = "R$ ${objective.value}"
        binding.textInformation.text = "${objective.name} - ${objective.date}"

    }

}