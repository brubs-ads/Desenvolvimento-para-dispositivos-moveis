package com.mycompany.confinance.view.objective

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import com.mycompany.confinance.controller.CreateObjectiveController
import com.mycompany.confinance.databinding.ActivityCreateObjectiveBinding
import java.text.SimpleDateFormat
import java.util.Calendar

class CreateObjectiveActivity : AppCompatActivity(),DatePickerDialog.OnDateSetListener {
    private lateinit var binding: ActivityCreateObjectiveBinding
    private val controller = CreateObjectiveController()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateObjectiveBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        val dateformat = SimpleDateFormat("yyyy-MM-dd")
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val date = dateformat.format(calendar.time)
        binding.buttonDate.text = date
    }

    private fun handleClick() {
        binding.button.setOnClickListener {
            addObjective()
        }
        binding.buttonDate.setOnClickListener {
            handleDate()
        }
    }

    private fun addObjective() {
        val name = binding.edittextNameMeta.text.toString()
        val value = binding.edittextValueObject.text.toString().toDouble()
        val data = binding.buttonDate.text.toString()

        controller.createObjective(name,value,data, onSuccess = {
            Toast.makeText(this,"Adicionado com Sucesso.",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,ObjectiveActivity::class.java))
            finish()
        }, onFailure = {
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        })
    }
    private fun handleDate() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this, this, year, month, day).show()
    }
}