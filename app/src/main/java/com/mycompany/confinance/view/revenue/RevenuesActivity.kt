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
import com.mycompany.confinance.view.adapter.RevenueAdapter
import com.mycompany.confinance.view.main.InitialActivity
import java.net.HttpURLConnection

class RevenuesActivity : AppCompatActivity() {


    private lateinit var binding: ActivityRevenuesBinding
    private val controller = RevenueController()
    private val adapter: RevenueAdapter by lazy {
        RevenueAdapter(::deleteMovement)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRevenuesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleClick()
        getMovement()

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
                recycler(it)
            }, onFailure = {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            })
    }
    private fun deleteMovement(movement: GetMovementModel) {
            controller.deleteMovementById(
                status = { message, code ->
                    if (code == HttpURLConnection.HTTP_OK) {
                        adapter.deleteRevenue(movement)
                        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                    }
                })
    }

    private fun recycler(list: List<GetMovementModel>) {
        binding.imageView.visibility = View.GONE
        binding.textCreateRevenues.visibility = View.GONE
        binding.textInformative.visibility = View.GONE

        binding.recyclerAllRevenues.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerAllRevenues.adapter = adapter
        adapter.updateRevenue(list)
    }
}

