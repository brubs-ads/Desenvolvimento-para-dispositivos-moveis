package com.mycompany.confinance.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.databinding.ActivityHouseCategoryBinding
import com.mycompany.confinance.databinding.ActivityOthersCategoryRevenueBinding

class OthersCategoryRevenueActivity: AppCompatActivity(){

    private lateinit var binding: ActivityOthersCategoryRevenueBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOthersCategoryRevenueBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}