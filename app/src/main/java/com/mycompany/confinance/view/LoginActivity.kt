package com.mycompany.confinance.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityLoginBinding
import com.mycompany.confinance.util.Constants

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        handleClick()

    }

    private fun handleClick() {
        binding.imageArrowBackLogin.setOnClickListener(this)
        binding.buttonLogin.setOnClickListener(this)
        binding.textTermsOfUseLogin.setOnClickListener(this)
        binding.textPrivacyPolicesLogin.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.image_arrow_back_login ->{

            }
            R.id.button_login ->{

            }
            R.id.text_terms_of_use_login ->{
                val intent = Intent(this,TermsOfUseActivity::class.java)
                intent.putExtra(Constants.REDIRECTION.KEY.LOGIN, Constants.REDIRECTION.VALUE.LOGIN)
                startActivity(intent)
                finish()
            }
            R.id.text_privacy_polices_login ->{
                val intent = Intent(this, PrivacyPolicesActivity::class.java)
                intent.putExtra(Constants.REDIRECTION.KEY.LOGIN, Constants.REDIRECTION.VALUE.LOGIN)
                startActivity(intent)
                finish()
            }
        }
    }


}