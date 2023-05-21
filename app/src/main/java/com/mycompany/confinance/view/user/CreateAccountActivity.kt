package com.mycompany.confinance.view.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mycompany.confinance.MainActivity
import com.mycompany.confinance.controller.CreateAccountController
import com.mycompany.confinance.databinding.ActivityCreateAccountBinding
import com.mycompany.confinance.util.Constants
import com.mycompany.confinance.view.main.MenuActivity
import com.mycompany.confinance.view.company.PrivacyPolicesActivity
import com.mycompany.confinance.view.company.TermsOfUseActivity
import com.mycompany.confinance.view.main.InitialActivity

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateAccountBinding
    private val controller = CreateAccountController()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar?.hide()

        handleClick()
    }

    private fun handleClick() {
        binding.imageArrowCreateAccount.setOnClickListener {
            val login = intent.getBooleanExtra(Constants.REDIRECTION.KEY.LOGIN, false)

            if (login) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
        binding.buttonAccount.setOnClickListener {
            handleAccount()

        }
        binding.textTermsOfUse.setOnClickListener {
            val intent = Intent(this, TermsOfUseActivity::class.java)
            intent.putExtra(
                Constants.REDIRECTION.KEY.ACCOUNT, Constants.REDIRECTION.VALUE.ACCOUNT
            )
            startActivity(intent)
            finish()

        }
        binding.textPrivacyPolices.setOnClickListener {
            val intent = Intent(this, PrivacyPolicesActivity::class.java)
            intent.putExtra(
                Constants.REDIRECTION.KEY.ACCOUNT, Constants.REDIRECTION.VALUE.ACCOUNT
            )
            startActivity(intent)
            finish()
        }
        binding.textHaveAccount.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra(
                Constants.REDIRECTION.KEY.ACCOUNT, Constants.REDIRECTION.VALUE.ACCOUNT
            )
            startActivity(intent)
            finish()
        }
    }

    private fun handleAccount() {
        val name = binding.editNameCreateAccount.text.toString()
        val email = binding.editEmailCreateAccount.text.toString().lowercase()
        val password = binding.editPasswordCreateAccount.text.toString()

        val validationAccount = controller.createAccount(name, email, password)

        if (validationAccount.status()) {
            startActivity(Intent(this, InitialActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, validationAccount.message(), Toast.LENGTH_LONG).show()
        }
    }
}