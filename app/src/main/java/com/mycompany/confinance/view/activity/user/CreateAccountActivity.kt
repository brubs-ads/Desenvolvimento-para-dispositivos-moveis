package com.mycompany.confinance.view.activity.user

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityCreateAccountBinding
import com.mycompany.confinance.view.activity.MainActivity
import com.mycompany.confinance.view.company.PrivacyActivity
import com.mycompany.confinance.view.company.TermsOfUseActivity
import com.mycompany.confinance.viewmodel.user.CreateAccountViewModel

class CreateAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAccountBinding
    private val viewModel: CreateAccountViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleCreateAccount()
        handleClick()
        observe()

    }

    private fun handleClick() {
        binding.textHaveAccount.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        val spannableString = SpannableString(getString(R.string.you_agree_terms))

        val termsOfUseClickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                val intent = Intent(applicationContext, TermsOfUseActivity::class.java)
                startActivity(intent)
            }
        }

        val privacyClickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                val intent = Intent(applicationContext, PrivacyActivity::class.java)
                startActivity(intent)
            }
        }

        spannableString.setSpan(
            termsOfUseClickableSpan,
            45,
            62,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            privacyClickableSpan,
            67,
            91,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val textView = binding.textYouAgreeTerms
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()

    }

    private fun handleCreateAccount() {
        binding.buttonCreateAccount.setOnClickListener {
            val name = binding.editEmailCreateAccount.text.toString()
            val email = binding.editEmailCreateAccount.text.toString()
            val password = binding.editPasswordCreateAccount.text.toString()
            viewModel.createAccount(name = name, email = email, password = password)
        }
    }

    private fun observe() {
        viewModel.isLoading.observe(this) { validation ->
            if (validation) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
            }
        }
    }
}
