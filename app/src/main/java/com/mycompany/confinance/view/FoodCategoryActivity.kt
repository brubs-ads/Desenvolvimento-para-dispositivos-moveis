package com.mycompany.confinance.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityFoodCategoryBinding


class FoodCategoryActivity : AppCompatActivity() {

        private lateinit var binding: ActivityFoodCategoryBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityFoodCategoryBinding.inflate(layoutInflater)
            setContentView(binding.root)
        }
}