package com.mycompany.confinance.view.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.RowObjectiveBinding
import com.mycompany.confinance.model.ObjectiveModel
import com.mycompany.confinance.util.OnClickMovementListener
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class ObjectiveViewHolder(private val binding: RowObjectiveBinding, private val listener: OnClickMovementListener) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(objective: ObjectiveModel) {
        when (objective.photo) {
            1 -> {
                binding.imageIcon.setImageResource(R.drawable.ob__1)
            }

            2 -> {
                binding.imageIcon.setImageResource(R.drawable.casa_obj_)
            }

            3 -> {
                binding.imageIcon.setImageResource(R.drawable.ob__3)
            }

            4 -> {
                binding.imageIcon.setImageResource(R.drawable.ob__4)
            }

            5 -> {
                binding.imageIcon.setImageResource(R.drawable.ob__5)
            }

            6 -> {
                binding.imageIcon.setImageResource(R.drawable.ob__6)
            }

            7 -> {
                binding.imageIcon.setImageResource(R.drawable.ob__7)
            }

            8 -> {
                binding.imageIcon.setImageResource(R.drawable.estudo_obj_)
            }
        }


        binding.textTypeObjective.text = objective.name
        binding.textDate.text = objective.date
        binding.textValueObjective.text = "Poupado ${formatarNumero(objective.value)}"
        binding.textValueSpared.text = "Objetivo ${formatarNumero(objective.value)}"
        binding.progressBarHorizontal.progress = objective.savedValue.toInt()
        binding.imgEdit.visibility = View.VISIBLE
        binding.trash.visibility = View.VISIBLE
        binding.trash.setOnClickListener {
            listener.delete(objective.id!!)
        }
        binding.imgEdit.setOnClickListener {
            listener.onClick(objective.id!!)
        }

        binding.shimmerLayout.hideShimmer()
        binding.shimmerLayout.stopShimmer()
    }

    fun bindShimmer() {
        binding.imageIcon.setImageDrawable(null)
        binding.progressBarHorizontal.progress = 5
    }

    private fun formatarNumero(numero: Double): String {
        val formato = DecimalFormat("#,##0.00", DecimalFormatSymbols(Locale("pt", "BR")))
        formato.isGroupingUsed = true
        formato.groupingSize = 3

        return "R$ " + formato.format(numero)
    }
}