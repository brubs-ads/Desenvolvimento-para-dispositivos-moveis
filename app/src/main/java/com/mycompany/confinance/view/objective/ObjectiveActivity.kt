package com.mycompany.confinance.view.objective

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityObjectiveBinding


class ObjectiveActivity : AppCompatActivity() {
    private lateinit var binding: ActivityObjectiveBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityObjectiveBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}