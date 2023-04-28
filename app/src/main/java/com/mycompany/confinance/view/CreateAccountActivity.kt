package com.mycompany.confinance.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import com.mycompany.confinance.MainActivity
import com.mycompany.confinance.databinding.ActivityCreateAccountBinding
import com.mycompany.confinance.util.Constants
import com.mycompany.confinance.viewmodel.CreateAccountViewModel

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateAccountBinding
    private lateinit var viewModel: CreateAccountViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[(CreateAccountViewModel::class.java)]

        supportActionBar?.hide()

        handleClick()
    }

    private fun handleClick() {
        binding.imageArrowCreateAccount.setOnClickListener {
            val login = intent.getBooleanExtra(Constants.REDIRECTION.KEY.LOGIN,false)

            if (login){
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }else{
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
        val email = binding.editEmailCreateAccount.text.toString()
        val password = binding.editEmailCreateAccount.text.toString()

        viewModel.createAccount(email,password)

    }


}