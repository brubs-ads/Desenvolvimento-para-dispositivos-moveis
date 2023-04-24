package com.mycompany.confinance.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.mycompany.confinance.MainActivity
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityCreateAccountBinding

class CreateAccountActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityCreateAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        handleClick()
    }

    private fun handleClick() {
        binding.imageArrowCreateAccount.setOnClickListener(this)
        binding.buttonAccount.setOnClickListener(this)
        binding.textTermsOfUse.setOnClickListener(this)
        binding.textPrivacyPolices.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.image_arrow_create_account -> {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            R.id.button_account -> {
                TODO("Não criei ainda, calma")

            }
            R.id.text_terms_of_use -> {
                startActivity(Intent(this, TermsOfUseActivity::class.java))
            }
            R.id.text_privacy_polices -> {
                startActivity(Intent(this,PrivacyPolicesActivity::class.java))
            }
        }
    }
}