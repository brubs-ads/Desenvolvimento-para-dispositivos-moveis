package com.mycompany.confinance.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.confinance.databinding.RowMovementBinding
import com.mycompany.confinance.model.MovementModel
import com.mycompany.confinance.view.viewHolder.MovementViewHolder

class MovementAdapter : RecyclerView.Adapter<MovementViewHolder>() {
    private var list: List<MovementModel> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovementViewHolder {
        val item = RowMovementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovementViewHolder(item)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MovementViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setList(list: List<MovementModel>) {
        this.list = list
    }
}