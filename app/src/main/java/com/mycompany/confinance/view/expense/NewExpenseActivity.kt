package com.mycompany.confinance.view.expense

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import com.mycompany.confinance.controller.NewExpenseController
import com.mycompany.confinance.databinding.ActivityNewExpenseBinding
import com.mycompany.confinance.model.movement.GetMovementModel
import com.mycompany.confinance.util.Constants
import com.mycompany.confinance.util.Constants.TEXT.EDIT_EXPENSE
import com.mycompany.confinance.util.Constants.TEXT.SAVE
import com.mycompany.confinance.view.main.InitialActivity
import java.text.SimpleDateFormat
import java.util.Calendar

class NewExpenseActivity : AppCompatActivity(),  DatePickerDialog.OnDateSetListener{

    private lateinit var binding: ActivityNewExpenseBinding
    private val controller = NewExpenseController()
    private var isEditing = false
    @SuppressLint("SimpleDateFormat")
    private val dateformat = SimpleDateFormat("yyyy-MM-dd")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleClick()
        getMovement()
        editExpense()
    }

    private fun handleClick() {
        binding.buttonCreate.setOnClickListener {
            addMovement()
        }
        binding.ButtonDate.setOnClickListener {
            handleDate()
        }
        binding.imageFecharXExpense.setOnClickListener {
            startActivity(Intent(this, InitialActivity::class.java))
            finish()
        }
    }

    private fun editExpense(){
        val selectedMovement = intent.getParcelableExtra<GetMovementModel>(Constants.TEXT.MOVEMENT)
        if (selectedMovement != null) {
            isEditing = true
            binding.textExpenseValue.setText(selectedMovement.value.toString())
            binding.textDescription.setText(selectedMovement.description)
            binding.ButtonDate.text = selectedMovement.date
            binding.buttonCreate.text = SAVE
            binding.textNewExpense.text = EDIT_EXPENSE
        }

    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val date = dateformat.format(calendar.time)
        binding.ButtonDate.text = date
    }

    private fun addMovement() {
        val value = binding.textExpenseValue.text.toString().toDouble()
        val description = binding.textDescription.text.toString()
        val date = binding.ButtonDate.text.toString()

        if (isEditing) {
            val selectedMovement = intent.getParcelableExtra<GetMovementModel>(Constants.TEXT.MOVEMENT)
            selectedMovement?.let {
                it.id?.let { it1 ->
                    controller.updateMovement(it1,value, description,date, onSuccess = {
                        Toast.makeText(this, "Atualizado com sucesso.", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, ExpensesActivity::class.java))
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
                    startActivity(Intent(this, ExpensesActivity::class.java))
                    finish()
                },
                onFailure = { message ->
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                }
            )
        }
    }

    private fun getMovement() {
        val movement = intent.getParcelableExtra<GetMovementModel>(Constants.TEXT.MOVEMENT)
        movement?.let {
            binding.textExpenseValue.setText(it.value.toString())
            binding.textDescription.setText(it.description)
            binding.ButtonDate.text = it.date
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

