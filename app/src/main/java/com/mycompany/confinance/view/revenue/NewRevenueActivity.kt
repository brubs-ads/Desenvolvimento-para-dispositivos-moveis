package com.mycompany.confinance.view.revenue

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.databinding.ActivityNewRevenueBinding

class NewRevenueActivity : AppCompatActivity() {
    private var initial: Double = 0.0
    private lateinit var binding: ActivityNewRevenueBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewRevenueBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}


