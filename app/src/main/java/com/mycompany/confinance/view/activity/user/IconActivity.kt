package com.mycompany.confinance.view.activity.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityIconBinding

class IconActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIconBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIconBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleClick()

    }

    private fun handleClick() {
        binding.arrowBack.setOnClickListener {
            finish()
        }
    }
}