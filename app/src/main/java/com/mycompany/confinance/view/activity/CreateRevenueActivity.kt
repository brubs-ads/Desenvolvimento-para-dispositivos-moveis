package com.mycompany.confinance.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityCreateRevenueBinding
import com.mycompany.confinance.viewmodel.CreateRevenueViewModel
import com.mycompany.confinance.viewmodel.HomeViewModel
import java.text.DecimalFormat

class CreateRevenueActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateRevenueBinding
    private val viewModel: CreateRevenueViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateRevenueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleRepetitions()
        handleSave()
    }

    private fun handleSave() {
        binding.buttonCreate.setOnClickListener {
            val value = binding.editBalanceRevenue.text.toString().toLong()
            val description = binding.editTextDescription.text.toString()
            val data = binding.textData.text.toString()
            val repetition = binding.numberPicker.value
            val fixa = binding.switchRevenue.isChecked

            viewModel.createRevenue(
                value = value,
                description = description,
                data = data,
                repetitions = repetition,
                fixedIncome = fixa,
                category = "1"
            )
        }
    }

    private fun handleRepetitions() {
        binding.numberPicker.minValue = 0
        binding.numberPicker.maxValue = 12

        binding.imageRepetition.setOnClickListener {
            if (binding.numberPicker.isEnabled) {
                binding.numberPicker.isEnabled = false
                binding.numberPicker.alpha = 0.5f
            } else {
                binding.numberPicker.isEnabled = true
                binding.numberPicker.alpha = 1.0f
            }
        }

    }
}