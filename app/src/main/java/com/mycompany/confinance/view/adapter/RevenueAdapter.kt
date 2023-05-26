package com.mycompany.confinance.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.confinance.databinding.RowRevenueBinding
import com.mycompany.confinance.model.movement.GetMovementModel
import com.mycompany.confinance.view.viewholder.RevenueViewHolder

class RevenueAdapter: RecyclerView.Adapter<RevenueViewHolder>() {
    private var listRevenue: List<GetMovementModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RevenueViewHolder {
        return RevenueViewHolder(RowRevenueBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listRevenue.count()
    }

    override fun onBindViewHolder(holder: RevenueViewHolder, position: Int) {
        holder.bind(listRevenue[position])
    }

    fun uptadeRevenue(list: List<GetMovementModel>) {
        listRevenue = list
    }
}