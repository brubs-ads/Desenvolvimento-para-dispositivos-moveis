package com.mycompany.confinance.view.revenue

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.controller.MovementController
import com.mycompany.confinance.databinding.ActivityNewRevenueBinding
import com.mycompany.confinance.model.movement.CreateMovementModel
import com.mycompany.confinance.util.Session
import java.util.Calendar

class NewRevenueActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewRevenueBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewRevenueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener { addMovement() }



    }

    private fun addMovement() {
        val value = binding.textRevenueValue.text.toString().toDoubleOrNull()
        val description = binding.edittextDescription.text.toString()

        if (value != null && description.isNotBlank()) {
            val typeMovement = "receita"
            val calendar = Calendar.getInstance()
            val date = calendar.time

            val movement = CreateMovementModel(
                type_movement = typeMovement,
                value = value,
                description = description,
                date = date
            )

            val movementController = MovementController()
            movementController.createMovement(
                movement.type_movement,
                movement.value,
                movement.description,
                movement.date,
                {
                    val total = movement.value + Session.total
                    Session.total = total
                    finish()
                }
            ) { message ->
                Toast.makeText(this, "Erro: $message", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
        }
    }
}


