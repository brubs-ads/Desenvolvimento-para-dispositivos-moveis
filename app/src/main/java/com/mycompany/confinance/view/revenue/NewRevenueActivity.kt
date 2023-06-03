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
import com.mycompany.confinance.model.movement.CreateMovementModel
import com.mycompany.confinance.model.objective.ObjectiveModel
import com.mycompany.confinance.util.Constants
import com.mycompany.confinance.util.Session
import com.mycompany.confinance.util.Session.movementId
import com.mycompany.confinance.view.expense.ExpensesActivity
import com.mycompany.confinance.view.main.InitialActivity
import com.mycompany.confinance.view.objective.ObjectiveActivity
import java.text.SimpleDateFormat
import java.util.Calendar

class NewRevenueActivity : AppCompatActivity(),
    DatePickerDialog.OnDateSetListener {
    private lateinit var binding: ActivityNewRevenueBinding
    @SuppressLint("SimpleDateFormat")
    private val dateformat = SimpleDateFormat("yyyy-MM-dd")
    private val controller = NewRevenueController()
    private var isEditing = false // Indica se está editando uma movimentação
    private var editmovement: CreateMovementModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewRevenueBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleClick()
        getMovementFromIntent()

        val editmovement = intent.getParcelableExtra<CreateMovementModel>("movement")
        if (editmovement != null) {
            isEditing = true
            this.editmovement = editmovement
            binding.textRevenueValue.setText(editmovement.value.toString())
            binding.edittextDescription.setText(editmovement.description)
            binding.buttonDate.text = editmovement.date
            binding.button.text = "Salvar"
            binding.textNewMovement.text = "Editar Receita"
        }
    }

    private fun handleClick() {
        binding.button.setOnClickListener {
         addMovement()
        }
        binding.buttonDate.setOnClickListener {
            handleDate()
        }
        binding.imageFecharXRevenue.setOnClickListener {
            startActivity(Intent(this, RevenuesActivity::class.java))
            finish()
        }
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val date = dateformat.format(calendar.time)
        binding.buttonDate.text = date
    }

    private fun addMovement() {
        val value = binding.textRevenueValue.text.toString().toDouble()
        val description = binding.edittextDescription.text.toString()
        val date = binding.buttonDate.text.toString()

        if (isEditing) {
            val selectedMovement = intent.getParcelableExtra<CreateMovementModel>("Movement")
            selectedMovement?.let {
                it.id?.let { it1 ->
                    controller.updateMovement(it1,value, description,date, onSuccess = {
                        Toast.makeText(this, "Atualizado com sucesso.", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, RevenuesActivity::class.java))
                        finish()
                    }, onFailure = {
                        Toast.makeText(baseContext, "Atualizado com sucesso.", Toast.LENGTH_SHORT)
                            .show()

                    })
                }
            }
        } else {
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
                }
            )
        }
    }

    private fun getMovementFromIntent() {
        val movement = intent.getParcelableExtra<CreateMovementModel>("movement")
        movement?.let {
            binding.textRevenueValue.setText(it.value.toString())
            binding.edittextDescription.setText(it.description)
            binding.buttonDate.text = it.date
        }
    }

    private fun handleDate() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this, this, year, month, day).show()
    }
}


