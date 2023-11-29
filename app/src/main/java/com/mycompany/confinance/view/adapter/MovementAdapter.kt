package com.mycompany.confinance.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.confinance.databinding.RowMovementBinding
import com.mycompany.confinance.model.MovementModel
import com.mycompany.confinance.util.OnClickMovementListener
import com.mycompany.confinance.view.viewHolder.MovementViewHolder

class MovementAdapter : RecyclerView.Adapter<MovementViewHolder>() {
    private var list: List<MovementModel> = listOf()
    private lateinit var listener: OnClickMovementListener
    private var showShimmer = true

    private companion object {
        private const val SHIMMER_ITEM_COUNT = 6
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovementViewHolder {
        val item = RowMovementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovementViewHolder(item,listener)
    }

    override fun getItemCount(): Int {
        return if (showShimmer) {
            SHIMMER_ITEM_COUNT
        } else {
            list.count()
        }
    }

    override fun onBindViewHolder(holder: MovementViewHolder, position: Int) {
        if (showShimmer){
            holder.bindShimmer()
        }else{
            holder.bind(list[position])
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun startShimmerAnimation() {
        showShimmer = true
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun stopShimmerAnimation() {
        showShimmer = false
        notifyDataSetChanged()
    }

    fun setList(list: List<MovementModel>) {
        showShimmer = false
        this.list = list
    }

    fun setListener(listener: OnClickMovementListener) {
        this.listener = listener
    }
}