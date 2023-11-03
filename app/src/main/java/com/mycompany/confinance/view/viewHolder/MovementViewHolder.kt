package com.mycompany.confinance.view.viewHolder

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.RowMovementBinding
import com.mycompany.confinance.model.MovementModel
import com.mycompany.confinance.util.OnClickMovementListener
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class MovementViewHolder(
    private val binding: RowMovementBinding, private val listener: OnClickMovementListener
) : RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("ResourceAsColor", "SetTextI18n")
    fun bind(movementModel: MovementModel) {
        if (movementModel.type_movement == "receita") {
            when (movementModel.Photo) {
                1 -> {
                    binding.imageIcon.setImageResource(R.drawable.ob__1)
                }

                2 -> {
                    binding.imageIcon.setImageResource(R.drawable.ob__1)
                }

                3 -> {
                    binding.imageIcon.setImageResource(R.drawable.ob__1)
                }

                4 -> {
                    binding.imageIcon.setImageResource(R.drawable.ob__1)
                }
            }
            binding.textValueMovement.setTextColor(R.color.green_4)
        }
        else {
            when (movementModel.Photo) {
                1 -> {
                    binding.imageIcon.setImageResource(R.drawable.ob__1)
                }

                2 -> {
                    binding.imageIcon.setImageResource(R.drawable.ob__1)
                }

                3 -> {
                    binding.imageIcon.setImageResource(R.drawable.ob__1)
                }

                4 -> {
                    binding.imageIcon.setImageResource(R.drawable.ob__1)
                }
            }
            binding.textValueMovement.setTextColor(R.color.red)
        }
        when (movementModel.recurrenceFrequency) {
            "weekly" -> {
                binding.textPeriod.text = "${movementModel.recurrenceIntervals}x Semanal"
            }

            "daily" -> {
                binding.textPeriod.text = "${movementModel.recurrenceIntervals}x Diário"
            }

            "monthly" -> {
                binding.textPeriod.text = "${movementModel.recurrenceIntervals}x Mensal"
            }

            "annually" -> {
                binding.textPeriod.text = "${movementModel.recurrenceIntervals}x Anual"
            }
        }

        binding.textTypeMovement.text = movementModel.description
        binding.textDate.text = movementModel.date
        binding.textValueMovement.text = formatarNumero(movementModel.value)
        binding.trash.visibility = View.VISIBLE
        binding.shimmerLayout.hideShimmer()
        binding.shimmerLayout.stopShimmer()

        binding.trash.setOnClickListener {
            listener.delete(movementModel.id!!)
        }
        binding.cardMovement.setOnClickListener {
            listener.onClick(movementModel.id!!)
        }

    }

    fun bindShimmer() {
        binding.imageIcon.setImageDrawable(null)
        binding.textValueMovement.text = ""
    }

    private fun formatarNumero(numero: Long): String {
        val formato = DecimalFormat("#,##0.00", DecimalFormatSymbols(Locale("pt", "BR")))
        formato.isGroupingUsed = true
        formato.groupingSize = 3

        return "R$ " + formato.format(numero)
    }
}