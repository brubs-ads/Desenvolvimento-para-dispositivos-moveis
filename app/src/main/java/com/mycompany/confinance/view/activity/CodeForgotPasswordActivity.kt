package com.mycompany.confinance.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.databinding.ActivityCodeForgotPasswordBinding

class CodeForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCodeForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCodeForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}