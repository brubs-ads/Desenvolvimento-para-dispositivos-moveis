package com.mycompany.confinance.view.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mycompany.confinance.MainActivity
import com.mycompany.confinance.controller.LoginController
import com.mycompany.confinance.databinding.ActivityLoginBinding
import com.mycompany.confinance.util.Constants
import com.mycompany.confinance.view.MenuActivity
import com.mycompany.confinance.view.company.PrivacyPolicesActivity
import com.mycompany.confinance.view.company.TermsOfUseActivity
import java.util.Locale

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val controller = LoginController()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        val email = binding.editEmailLogin.text.toString().lowercase()
        val password = binding.editPasswordLogin.text.toString()

        val validationLogin = controller.login(email, password)
        if (validationLogin.status()) {
            startActivity(Intent(this, MenuActivity::class.java))
        } else {
            Toast.makeText(this, validationLogin.message(), Toast.LENGTH_LONG).show()
        }
    }
}