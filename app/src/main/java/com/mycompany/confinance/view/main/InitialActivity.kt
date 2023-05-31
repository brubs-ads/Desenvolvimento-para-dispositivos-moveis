package com.mycompany.confinance.view.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.R
import com.mycompany.confinance.controller.InitialController
import com.mycompany.confinance.databinding.ActivityInitialBinding
import com.mycompany.confinance.util.Session
import com.mycompany.confinance.view.expense.ExpensesActivity
import com.mycompany.confinance.view.revenue.RevenuesActivity

class InitialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInitialBinding
    private val controller = InitialController()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInitialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleTotal()
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

    private fun handleTotal() {
        controller.getTotal(
            onSuccess = {
                binding.textBalance.text = it.total.toString()
                binding.textRevenuesTotal.text = it.totalRevenues.toString()
                binding.textExpensesTotal.text = it.totalExpenses.toString()
            }, onFailure = {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        )
    }

}