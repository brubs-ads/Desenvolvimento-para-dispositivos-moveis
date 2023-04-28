package com.mycompany.confinance.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mycompany.confinance.MainActivity
import com.mycompany.confinance.MenuActivity
import com.mycompany.confinance.databinding.ActivityLoginBinding
import com.mycompany.confinance.util.Constants
import com.mycompany.confinance.viewmodel.CreateAccountViewModel
import com.mycompany.confinance.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel:LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        supportActionBar?.hide()

        handleClick()

    }

    private fun handleClick() {
        binding.imageArrowBackLogin.setOnClickListener {

            val account = intent.getBooleanExtra(Constants.REDIRECTION.KEY.ACCOUNT, false)
            if (account) {
                startActivity(Intent(this, CreateAccountActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }

        }
        binding.buttonLogin.setOnClickListener {
            handleLogin()
        }

        binding.textTermsOfUseLogin.setOnClickListener {

            val intent = Intent(this, TermsOfUseActivity::class.java)
            intent.putExtra(Constants.REDIRECTION.KEY.LOGIN, Constants.REDIRECTION.VALUE.LOGIN)
            startActivity(intent)
            finish()

        }
        binding.textPrivacyPolicesLogin.setOnClickListener {
            val intent = Intent(this, PrivacyPolicesActivity::class.java)
            intent.putExtra(Constants.REDIRECTION.KEY.LOGIN, Constants.REDIRECTION.VALUE.LOGIN)
            startActivity(intent)
            finish()

        }

        binding.textCreateAccountLogin.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            intent.putExtra(Constants.REDIRECTION.KEY.LOGIN, Constants.REDIRECTION.VALUE.LOGIN)
            startActivity(intent)
            finish()
        }
    }

    private fun handleLogin() {
        val email = binding.editEmailLogin.text.toString()
        val password = binding.editPasswordLogin.text.toString()

        viewModel.doLogin(email,password)
    }
}