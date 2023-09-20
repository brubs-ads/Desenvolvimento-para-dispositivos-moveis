package com.mycompany.confinance.view.activity.user

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.SpannableString
import android.text.method.HideReturnsTransformationMethod
import android.text.method.LinkMovementMethod
import android.text.method.PasswordTransformationMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityLoginBinding
import com.mycompany.confinance.view.activity.MainActivity
import com.mycompany.confinance.view.company.PrivacyActivity
import com.mycompany.confinance.view.company.TermsOfUseActivity
import com.mycompany.confinance.viewmodel.user.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        handleClick()
        handleTerms()
        observe()

    }

    private fun handleTerms() {
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

    private fun observe() {
        viewModel.isLoading.observe(this) { loading ->
            if (loading) {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            } else {
                viewModel.error.observe(this){
                    Toast.makeText(applicationContext,it.toString(),Toast.LENGTH_LONG).show()
                }
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

        binding.imageEye.setOnClickListener {
            val edit = binding.editPasswordLogin
            if (edit.transformationMethod == PasswordTransformationMethod.getInstance()) {
                edit.transformationMethod = null
                binding.imageEye.setImageResource(R.drawable.olho_fechado)
            } else {
                edit.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.imageEye.setImageResource(R.drawable.olho_aberto)
            }

        }

    }
}