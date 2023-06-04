package com.mycompany.confinance.view.viewholder

import android.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.confinance.databinding.RowRecyclerBinding
import com.mycompany.confinance.model.movement.GetMovementModel
import com.mycompany.confinance.view.OnMovementListener

class ExpenseViewHolder(
    private val bind: RowRecyclerBinding,
    private val listener: OnMovementListener
) : RecyclerView.ViewHolder(bind.root) {

    fun bind(movement: GetMovementModel) {
        bind.textInformation.text = "${movement.description} - ${movement.date}"
        bind.textValue.text = "$ ${movement.value}"

        bind.viewRecycler.setOnClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Edição da movimentação")
                .setMessage("Você deseja editar a movimentação?")
                .setPositiveButton("Sim") { dialog, which ->
                    listener.onClick(movement.id!!)
                }
                .setNegativeButton("Cancelar",null)
                .create()
                .show()
        }
        bind.viewRecycler.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
           .setTitle("Remoção da movimentação")
           .setMessage("Você tem certeza que deseja remover?")
           .setPositiveButton("Sim") { dialog, which ->
              listener.onDelete(movement.id!!)
                }
                .setNegativeButton("Cancelar", null)
                .create()
                .show()

            true
        }
    }
}
