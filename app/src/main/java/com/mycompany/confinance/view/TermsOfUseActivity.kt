package com.mycompany.confinance.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mycompany.confinance.databinding.ActivityTermsOfUseBinding

class TermsOfUseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTermsOfUseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsOfUseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        supportActionBar?.hide()

        binding.imageArrowBackTermsOfUse.setOnClickListener {
            val intent = Intent(applicationContext,CreateAccountActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}