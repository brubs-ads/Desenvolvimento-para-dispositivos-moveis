package com.mycompany.confinance.view.company

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mycompany.confinance.databinding.ActivityPrivacyPolicesBinding
import com.mycompany.confinance.util.Constants
import com.mycompany.confinance.view.user.CreateAccountActivity
import com.mycompany.confinance.view.user.LoginActivity

class PrivacyPolicesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPrivacyPolicesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrivacyPolicesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val account = intent.getBooleanExtra(Constants.REDIRECTION.KEY.ACCOUNT,false)
        val login = intent.getBooleanExtra(Constants.REDIRECTION.KEY.LOGIN,false)

        handleClick(account =  account, login = login)

    }

    private fun handleClick(account:Boolean, login: Boolean ) {
        binding.imageArrowBackPrivacyPolices.setOnClickListener {
            if (account){
                startActivity(Intent(this, CreateAccountActivity::class.java))
                finish()
            }else if (login){
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }

        }
    }
}