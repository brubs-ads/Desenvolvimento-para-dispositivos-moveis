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
import com.mycompany.confinance.view.OnMovementListener
import com.mycompany.confinance.view.adapter.ExpenseAdapter
import com.mycompany.confinance.view.adapter.RevenueAdapter
import com.mycompany.confinance.view.main.InitialActivity
import com.mycompany.confinance.view.revenue.NewRevenueActivity

class ExpensesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExpensesBinding
    private val controller = ExpenseController()
    private val adapter = ExpenseAdapter()
    private var listExpense: ArrayList<GetMovementModel> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExpensesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleClick()
        getMovement()
    }

    override fun onResume() {
        super.onResume()
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
                listExpense = it as ArrayList
                recycler()
            }, onFailure = {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            })
    }

    private fun updateMovements(id: Long) {
        var position = 0
        var movement: GetMovementModel? = null
        for (i in 0 until listExpense.size) {
            if (listExpense[i].id == id) {
                position = i
                movement = listExpense[i]
            }
        }
        listExpense.remove(movement)
        adapter.notifyItemRemoved(position)
    }

    private fun handleMovement() {
        val listener = object : OnMovementListener {
            override fun onClick(id: Long) {
                Toast.makeText(applicationContext, "clicooouu", Toast.LENGTH_SHORT).show()
            }

            override fun onDelete(id: Long) {
                controller.deleteMovementById(id, result = { message, status ->
                    if (status) {
                        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
                        updateMovements(id)
                    } else {
                        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
                    }
                })
            }

        }
        adapter.movementClick(listener)
    }

    private fun recycler() {
        binding.imageView.visibility = View.GONE
        binding.textExpenses.visibility = View.GONE
        binding.textInformative.visibility = View.GONE
        binding.recyclerAllExpenses.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerAllExpenses.adapter = adapter

        adapter.updateExpense(listExpense)
        handleMovement()
    }
    }
