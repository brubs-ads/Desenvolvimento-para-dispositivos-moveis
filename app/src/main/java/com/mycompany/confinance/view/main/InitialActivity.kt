package com.mycompany.confinance.view.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.databinding.ActivityInitialBinding
import com.mycompany.confinance.view.expense.ExpensesActivity
import com.mycompany.confinance.view.revenue.RevenuesActivity

class InitialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInitialBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInitialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleclick()
    }

    private fun handleclick() {

        binding.textRevenueCategory.setOnClickListener {
            val revenuecategoryintent = Intent(this, RevenuesActivity::class.java)
            startActivity(revenuecategoryintent)
        }
        binding.textExpenseCategory.setOnClickListener {
            val expensecategoryintent = Intent(this, ExpensesActivity::class.java)
            startActivity(expensecategoryintent)
        }
        binding.imageMenuInitial.setOnClickListener {
            val menuinitalintent = Intent(this, MenuActivity::class.java)
            startActivity(menuinitalintent)
        }
    }
}