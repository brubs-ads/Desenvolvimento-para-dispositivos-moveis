package com.mycompany.confinance.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityInvestmentCategoryBinding

class InvestmentCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInvestmentCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInvestmentCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}