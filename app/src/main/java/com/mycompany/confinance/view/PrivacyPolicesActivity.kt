package com.mycompany.confinance.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityPrivacyPolicesBinding

class PrivacyPolicesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrivacyPolicesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrivacyPolicesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}