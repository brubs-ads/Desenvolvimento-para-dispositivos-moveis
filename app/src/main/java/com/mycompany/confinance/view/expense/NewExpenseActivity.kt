package com.mycompany.confinance.view.expense

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import com.mycompany.confinance.R
import com.mycompany.confinance.controller.NewExpenseController
import com.mycompany.confinance.controller.NewRevenueController
import com.mycompany.confinance.databinding.ActivityNewExpenseBinding
import com.mycompany.confinance.databinding.ActivityNewRevenueBinding
import com.mycompany.confinance.view.revenue.RevenuesActivity
import java.text.SimpleDateFormat
import java.util.Calendar

class NewExpenseActivity : AppCompatActivity(),  DatePickerDialog.OnDateSetListener{

    private lateinit var binding: ActivityNewExpenseBinding

    @SuppressLint("SimpleDateFormat")
    private val dateformat = SimpleDateFormat("yyyy-MM-dd")

    private val controller = NewExpenseController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewExpenseBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_new_expense)
    }

    private fun handleClick(){
        binding.buttonCreate.setOnClickListener {
            addMovement()
        }
        binding.ButtonDate.setOnClickListener {
            handleDate()
        }
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year,month,dayOfMonth)
        val date = dateformat.format(calendar.time)
        binding.ButtonDate.text = date
    }
    private fun addMovement() {
        val value = binding.textExpenseValue.toString().toDouble()
        val description = binding.textDescription.text.toString()
        val date = binding.ButtonDate.text.toString()

        controller.createMovement(
            value,
            description,
            date,
            onSuccess = {
                Toast.makeText(this, "Movimento criado com Sucesso!", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, RevenuesActivity::class.java))
                finish()
            },
            onFailure = { message ->
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            } )
    }

    private fun handleDate(){
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this,this,year,month,day).show()
    }
}