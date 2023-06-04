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
import com.mycompany.confinance.model.objective.ObjectiveModel
import com.mycompany.confinance.util.Constants.TEXT.EDIT_OBJECTIVE
import com.mycompany.confinance.util.Constants.TEXT.OBJECTIVE
import com.mycompany.confinance.util.Constants.TEXT.SAVE
import java.text.SimpleDateFormat
import java.util.Calendar

class CreateObjectiveActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    private lateinit var binding: ActivityCreateObjectiveBinding
    private val controller = CreateObjectiveController()
    private var isEditing = false
    @SuppressLint("SimpleDateFormat")
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateObjectiveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleClick()
        getObjectiveFromIntent()

        val selectedObjective = intent.getParcelableExtra<ObjectiveModel>(OBJECTIVE)
        if (selectedObjective != null) {
            isEditing = true
            binding.edittextNameMeta.setText(selectedObjective.name)
            binding.edittextValueObject.setText(selectedObjective.value.toString())
            binding.buttonDate.text = selectedObjective.date
            binding.button.text = SAVE
            binding.textviewNewObject.text = EDIT_OBJECTIVE
        }
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val date = dateFormat.format(calendar.time)
        binding.buttonDate.text = date
    }

    private fun handleClick() {
        binding.button.setOnClickListener {
            addObjective()
        }
        binding.buttonDate.setOnClickListener {
            handleDate()
        }
        binding.imageBackObject.setOnClickListener{
            startActivity(Intent(this, ObjectiveActivity::class.java))
            finish()
        }
    }

    private fun addObjective() {
        val name = binding.edittextNameMeta.text.toString()
        val value = binding.edittextValueObject.text.toString().toDouble()
        val date = binding.buttonDate.text.toString()

        if (isEditing) {
            val selectedObjective = intent.getParcelableExtra<ObjectiveModel>(OBJECTIVE)
            selectedObjective?.let {
                it.id?.let { it1 ->
                    controller.updateObjective(it1, name, value, date, onSuccess = {
                        Toast.makeText(this, "Atualizado com sucesso.", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, ObjectiveActivity::class.java))
                        finish()
                    }, onFailure = {
                        Toast.makeText(baseContext, "Atualizado com sucesso.", Toast.LENGTH_SHORT).show()

                    })
                }
            }
        } else {
            controller.createObjective(name, value, date, onSuccess = {
                Toast.makeText(this, "Adicionado com sucesso.", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, ObjectiveActivity::class.java))
                finish()
            }, onFailure = {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            })
        }
    }

    private fun getObjectiveFromIntent() {
        val objective = intent.getParcelableExtra<ObjectiveModel>(OBJECTIVE)
        objective?.let {
            binding.edittextNameMeta.setText(it.name)
            binding.edittextValueObject.setText(it.value.toString())
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
