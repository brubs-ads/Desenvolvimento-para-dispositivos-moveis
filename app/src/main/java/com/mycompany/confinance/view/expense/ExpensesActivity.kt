package com.mycompany.confinance.view.expense

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.databinding.ActivityExpensesBinding

class ExpensesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExpensesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExpensesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}