package com.mycompany.confinance.view.activity.objective

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mycompany.confinance.databinding.ActivityObjectiveBinding

class ObjectiveActivity : AppCompatActivity() {
    private lateinit var binding: ActivityObjectiveBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityObjectiveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleClick()

    }

    private fun handleClick() {
        binding.imgBack.setOnClickListener {
            finish()
        }

        binding.buttonCreateObjective.setOnClickListener {
            startActivity(Intent(this, ObjectiveActivity::class.java))
            finish()
        }
    }
}