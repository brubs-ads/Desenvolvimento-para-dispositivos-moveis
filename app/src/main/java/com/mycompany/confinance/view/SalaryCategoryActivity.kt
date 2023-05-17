package com.mycompany.confinance.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivitySalaryCategoryBinding

class SalaryCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySalaryCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySalaryCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}