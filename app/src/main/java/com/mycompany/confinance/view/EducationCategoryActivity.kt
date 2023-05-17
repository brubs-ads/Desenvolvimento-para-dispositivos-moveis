package com.mycompany.confinance.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityEducationCategoryBinding


class EducationCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEducationCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEducationCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}