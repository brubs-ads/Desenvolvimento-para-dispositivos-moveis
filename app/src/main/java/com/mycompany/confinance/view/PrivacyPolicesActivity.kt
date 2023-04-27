package com.mycompany.confinance.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityPrivacyPolicesBinding
import com.mycompany.confinance.util.Constants
import kotlin.math.log

class PrivacyPolicesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPrivacyPolicesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrivacyPolicesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val account = intent.getBooleanExtra(Constants.REDIRECTION.KEY.ACCOUNT,false)
        val login = intent.getBooleanExtra(Constants.REDIRECTION.KEY.LOGIN,false)

        binding.imageArrowBackPrivacyPolices.setOnClickListener {
            if (account){
                startActivity(Intent(this,CreateAccountActivity::class.java))
                finish()
            }else if (login){
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }

        }
    }
}