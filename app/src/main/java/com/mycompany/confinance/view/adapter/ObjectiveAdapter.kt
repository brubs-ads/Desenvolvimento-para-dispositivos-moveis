package com.mycompany.confinance.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.confinance.databinding.RowRecyclerBinding
import com.mycompany.confinance.model.objective.ObjectiveModel
import com.mycompany.confinance.view.OnMovementListener
import com.mycompany.confinance.view.viewholder.ObjectiveViewHolder

class ObjectiveAdapter(): RecyclerView.Adapter<ObjectiveViewHolder>() {
    private var listObjective: List<ObjectiveModel> = listOf()
    private lateinit var listener: OnMovementListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectiveViewHolder {
        val item = RowRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ObjectiveViewHolder(item,listener)
    }

    override fun getItemCount(): Int {
        return listObjective.count()
    }

    override fun onBindViewHolder(holder: ObjectiveViewHolder, position: Int) {
        holder.bind(listObjective[position])
    }

    fun updateObjective(list: List<ObjectiveModel>) {
        listObjective = list
    }

    fun clickObjective(click : OnMovementListener){
        listener = click
    }
}