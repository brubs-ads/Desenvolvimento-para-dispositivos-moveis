package com.mycompany.confinance.view.viewholder

import android.app.AlertDialog
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

        binding.viewRecycler.setOnClickListener{
            listener.onClick(objective.id!!)
        }
        binding.viewRecycler.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção do objetivo")
                .setMessage("Você tem certeza que deseja remover?")
                .setPositiveButton("Sim") { dialog, which ->
                    listener.onDelete(objective.id!!)
                }
                .setNegativeButton("Cancelar", null)
                .create()
                .show()

            true
        }
    }

}