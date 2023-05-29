package com.mycompany.confinance.view.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityInitialBinding
import com.mycompany.confinance.util.Session
import com.mycompany.confinance.view.expense.ExpensesActivity
import com.mycompany.confinance.view.revenue.RevenuesActivity

class InitialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInitialBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInitialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textBalance.text = String.format("%.2f", Session.total)

        handleClick()

    }

    private fun handleClick() {

        binding.textRevenueCategory.setOnClickListener {
            val revenuecategoryintent = Intent(this, RevenuesActivity::class.java)
            startActivity(revenuecategoryintent)
        }
        binding.textExpenseCategory.setOnClickListener {
            val expensecategoryintent = Intent(this, ExpensesActivity::class.java)
            startActivity(expensecategoryintent)
        }
        binding.imageMenuInitial.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }
    }
}