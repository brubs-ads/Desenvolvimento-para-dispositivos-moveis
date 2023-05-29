package com.mycompany.confinance.view.revenue

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.controller.NewRevenueController
import com.mycompany.confinance.databinding.ActivityNewRevenueBinding
import com.mycompany.confinance.util.Session
import com.mycompany.confinance.view.main.InitialActivity
import java.text.SimpleDateFormat
import java.util.Calendar

class NewRevenueActivity : AppCompatActivity(),
    DatePickerDialog.OnDateSetListener {

    private lateinit var binding: ActivityNewRevenueBinding
    @SuppressLint("SimpleDateFormat")
    private val dateformat = SimpleDateFormat("yyyy-MM-dd")
    private val controller = NewRevenueController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewRevenueBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleClick()

    }
    private fun handleClick(){
        binding.button.setOnClickListener {
            addMovement()
        }
        binding.buttonDate.setOnClickListener {
            handleDate()
        }
    }


    override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year,month,dayOfMonth)
        val date = dateformat.format(calendar.time)
        binding.buttonDate.text = date
    }

    private fun addMovement() {
        val value = binding.textRevenueValue.text.toString().toDouble()
        val description = binding.edittextDescription.text.toString()
        val date = binding.buttonDate.text.toString()

        controller.createMovement(
            value,
            description,
            date,
            onSuccess = {
                Toast.makeText(this, "Movimento criado com Sucesso!", Toast.LENGTH_LONG).show()
                startActivity(Intent(this,RevenuesActivity::class.java))
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


