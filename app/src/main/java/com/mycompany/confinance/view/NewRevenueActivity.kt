package com.mycompany.confinance.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.databinding.ActivityNewRevenueBinding

class NewRevenueActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewRevenueBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewRevenueBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}