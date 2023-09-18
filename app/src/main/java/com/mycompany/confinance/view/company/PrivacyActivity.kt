package com.mycompany.confinance.view.company

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.databinding.ActivityPrivacyBinding

class PrivacyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPrivacyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrivacyBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}