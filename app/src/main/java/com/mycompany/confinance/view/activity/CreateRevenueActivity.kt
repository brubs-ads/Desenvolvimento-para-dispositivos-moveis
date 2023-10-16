package com.mycompany.confinance.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityCreateRevenueBinding

class CreateRevenueActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateRevenueBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateRevenueBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}