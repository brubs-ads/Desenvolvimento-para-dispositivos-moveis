package com.mycompany.confinance.view.activity.user

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.databinding.ActivityLoginBinding
import com.mycompany.confinance.view.activity.MainActivity
import com.mycompany.confinance.viewmodel.user.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        handleClick()
        observe()

    }

    private fun observe() {
        viewModel.isLoading.observe(this) { loading ->
            if (loading) {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            } else {

            }
        }
    }

    private fun handleClick() {
        binding.textAccount.setOnClickListener {
            startActivity(Intent(applicationContext, CreateAccountActivity::class.java))
            finish()
        }
        binding.buttonLogin.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val password = binding.editPasswordLogin.text.toString()
            viewModel.login(email = email, password = password)
        }

        binding.textForgotPassword.setOnClickListener {
            startActivity(Intent(applicationContext, ForgotPasswordActivity::class.java))
            finish()
        }
    }
}