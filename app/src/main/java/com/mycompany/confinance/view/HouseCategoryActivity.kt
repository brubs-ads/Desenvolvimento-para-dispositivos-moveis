package com.mycompany.confinance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mycompany.confinance.databinding.ActivityHouseCategoryBinding

class HouseCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHouseCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHouseCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}