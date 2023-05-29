package com.mycompany.confinance.view.expense

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mycompany.confinance.controller.ExpenseController
import com.mycompany.confinance.controller.RevenueController
import com.mycompany.confinance.databinding.ActivityExpensesBinding
import com.mycompany.confinance.model.movement.GetMovementModel
import com.mycompany.confinance.view.adapter.ExpenseAdapter
import com.mycompany.confinance.view.adapter.RevenueAdapter
import com.mycompany.confinance.view.main.InitialActivity
import com.mycompany.confinance.view.revenue.NewRevenueActivity

class ExpensesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExpensesBinding
    private val controller = ExpenseController()
    private val adapter = ExpenseAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExpensesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleClick()
        getMovement()
    }

    private fun handleClick() {
        binding.imageAddExpenses.setOnClickListener {
            startActivity(Intent(this, NewExpenseActivity::class.java))
            finish()
        }
        binding.imageBackArrowExpense.setOnClickListener {
            startActivity(Intent(this, InitialActivity::class.java))
            finish()
        }
    }

    private fun getMovement() {
        controller.getMovementUserId(
            onSuccess = {
                recycler(it)
            }, onFailure = {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            })
    }

    private fun recycler(list: List<GetMovementModel>) {
        binding.imageView.visibility = View.GONE
        binding.textExpenses.visibility = View.GONE
        binding.textInformative.visibility = View.GONE

        binding.recyclerAllExpenses.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerAllExpenses.adapter = adapter
        adapter.updateExpense(list)
    }
}