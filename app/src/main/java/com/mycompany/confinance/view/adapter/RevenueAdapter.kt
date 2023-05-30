package com.mycompany.confinance.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.confinance.databinding.RowRevenueBinding
import com.mycompany.confinance.model.movement.GetMovementModel
import com.mycompany.confinance.view.viewholder.RevenueViewHolder

class RevenueAdapter(
    private val onLongClickListener: (GetMovementModel) -> Unit
) : RecyclerView.Adapter<RevenueViewHolder>() {
    private var listRevenue: List<GetMovementModel> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RevenueViewHolder {
        val item = RowRevenueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RevenueViewHolder(item, onLongClickListener)
    }

    override fun getItemCount(): Int {
        return listRevenue.size
    }

    override fun onBindViewHolder(holder: RevenueViewHolder, position: Int) {
        holder.bind(listRevenue[position])
    }

    fun updateRevenue(list: List<GetMovementModel>) {
        listRevenue = list
    }

    fun deleteRevenue(movement: GetMovementModel) {
        val position = listRevenue.indexOf(movement)
        if (position != -1) {
            listRevenue = listRevenue.toMutableList().apply { removeAt(position) }
            notifyItemRemoved(position)
        }
    }
}