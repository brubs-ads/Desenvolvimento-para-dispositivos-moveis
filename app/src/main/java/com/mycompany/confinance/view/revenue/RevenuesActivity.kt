package com.mycompany.confinance.view.revenue

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mycompany.confinance.controller.RevenueController
import com.mycompany.confinance.databinding.ActivityRevenuesBinding
import com.mycompany.confinance.model.movement.GetMovementModel
import com.mycompany.confinance.view.OnMovementListener
import com.mycompany.confinance.view.adapter.RevenueAdapter
import com.mycompany.confinance.view.main.InitialActivity
import java.net.HttpURLConnection

class RevenuesActivity : AppCompatActivity() {


    private lateinit var binding: ActivityRevenuesBinding
    private val controller = RevenueController()
    private val adapter = RevenueAdapter()
    private var listRevenue: ArrayList<GetMovementModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRevenuesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleClick()
        getMovement()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun handleClick() {
        binding.imageAddRevenues.setOnClickListener {
            startActivity(Intent(this, NewRevenueActivity::class.java))
            finish()
        }
        binding.imageBackArrowRevenues.setOnClickListener {
            startActivity(Intent(this, InitialActivity::class.java))
            finish()
        }
    }

    private fun getMovement() {
        controller.getMovementUserId(
            onSuccess = {
                listRevenue = it as ArrayList
                recycler()
            }, onFailure = {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            })
    }

    private fun updateMovements(id: Long) {
        var position = 0
        var movement: GetMovementModel? = null
        for (i in 0 until listRevenue.size){
            if (listRevenue[i].id == id) {
                position = i
                movement = listRevenue[i]
            }
        }
        listRevenue.remove(movement)
        adapter.notifyItemRemoved(position)
    }

    private fun handleMovement() {
        val listener = object : OnMovementListener {
            override fun onClick(id: Long) {
                Toast.makeText(applicationContext, "clicooouu", Toast.LENGTH_SHORT).show()
            }

            override fun onDelete(id: Long) {
                controller.deleteMovementById(id, result = {
                        message, status ->
                    if (status){
                        Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()
                        updateMovements(id)
                    }else{
                        Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()
                    }
                })
            }

        }
        adapter.movementClick(listener)
    }

    private fun recycler() {
        binding.imageView.visibility = View.GONE
        binding.textCreateRevenues.visibility = View.GONE
        binding.textInformative.visibility = View.GONE

        binding.recyclerAllRevenues.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerAllRevenues.adapter = adapter
        adapter.updateRevenue(listRevenue)
        handleMovement()
    }

}

