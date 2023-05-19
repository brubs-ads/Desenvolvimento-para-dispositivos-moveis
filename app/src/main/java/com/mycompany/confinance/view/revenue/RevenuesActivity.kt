package com.mycompany.confinance.view.revenue

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.databinding.ActivityRevenuesBinding

class RevenuesActivity : AppCompatActivity() {


    private lateinit var binding: ActivityRevenuesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRevenuesBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}

