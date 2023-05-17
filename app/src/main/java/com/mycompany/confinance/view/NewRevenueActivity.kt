package com.mycompany.confinance.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.format.DateFormat.format
import android.view.View
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.internal.bind.util.ISO8601Utils.format
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityNewRevenueBinding
import com.mycompany.confinance.model.MovementModel
import java.text.SimpleDateFormat

import java.util.*

class NewRevenueActivity : AppCompatActivity(), View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private lateinit var binding: ActivityNewRevenueBinding
    private val dateFormart = SimpleDateFormat("dd/MM/yyyy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewRevenueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonDate.setOnClickListener(this)
        binding.buttonCreate.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_date){
            handleDate()
        }else if(v.id == R.id.button_create){
            handleCreate()
        }

    }

    override fun onDateSet(v: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year,month,dayOfMonth)
        val date = dateFormart.format(calendar.time)
        binding.buttonDate.text = date
    }

    private fun handleCreate(){
        val movement = MovementModel().apply {
            this.id = 0
            val stringValue = binding.textRevenueValue.text.toString()
            val doubleValue = stringValue.toDouble()
            this.value = doubleValue
            this.description = binding.edittextDescription.text.toString()
            this.date = binding.buttonDate.text.toString()

        }
    }


    private fun handleDate(){
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,this, year , month ,day).show()
    }
}