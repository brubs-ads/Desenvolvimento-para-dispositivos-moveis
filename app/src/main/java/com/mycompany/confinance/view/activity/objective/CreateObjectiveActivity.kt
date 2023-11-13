package com.mycompany.confinance.view.activity.objective

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityCreateObjectiveBinding

class CreateObjectiveActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateObjectiveBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateObjectiveBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}