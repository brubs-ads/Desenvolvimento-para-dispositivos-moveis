package com.mycompany.confinance.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.databinding.ActivityHealthCategoryBinding

class HealthCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHealthCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHealthCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}