package com.mycompany.confinance.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.databinding.ActivityHouseCategoryBinding
import com.mycompany.confinance.databinding.ActivityServicesCategoryBinding

class ServicesCategoryActivity: AppCompatActivity() {

    private lateinit var binding: ActivityServicesCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServicesCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}