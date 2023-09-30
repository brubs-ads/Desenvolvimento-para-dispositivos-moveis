package com.mycompany.confinance.view.activity.user

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.method.PasswordTransformationMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityLoginBinding
import com.mycompany.confinance.databinding.CustomDialogLoginAuthenticationBinding
import com.mycompany.confinance.databinding.CustomDialogNoConnectionLoginCreateAccountBinding
import com.mycompany.confinance.view.activity.MainActivity
import com.mycompany.confinance.view.company.PrivacyActivity
import com.mycompany.confinance.view.company.TermsOfUseActivity
import com.mycompany.confinance.viewmodel.user.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var dialogNoConnection: AlertDialog? = null
    private var dialogNoAuthentication: AlertDialog? = null

    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleClick()
        handleTerms()
        observe()

    }

    @SuppressLint("ResourceAsColor")
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
            46,
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
                viewModel.error.observe(this) { response ->
                    if (response.code == 500){
                        if (dialogNoConnection != null && dialogNoConnection?.isShowing == true) {
                            dialogNoConnection?.dismiss()
                        }

                        val build = AlertDialog.Builder(this, R.style.ThemeCustomDialog)
                        val dialogBinding =
                            CustomDialogNoConnectionLoginCreateAccountBinding.inflate(
                                LayoutInflater.from(this)
                            )
                        dialogBinding.textDescription.text = response.message
                        dialogBinding.textAgain.setOnClickListener {
                            dialogNoConnection?.dismiss()
                        }
                        dialogBinding.view.setOnClickListener {
                            dialogNoConnection?.dismiss()
                        }

                        dialogNoConnection = build.setView(dialogBinding.root).create()
                        dialogNoConnection?.show()

                    }
                    else{
                        if (dialogNoAuthentication != null && dialogNoAuthentication?.isShowing == true) {
                            dialogNoAuthentication?.dismiss()
                        }

                        val build = AlertDialog.Builder(this, R.style.ThemeCustomDialog)
                        val dialogBinding =
                            CustomDialogLoginAuthenticationBinding.inflate(
                                LayoutInflater.from(
                                    this
                                )
                            )

                        dialogBinding.textInformationAuthentication.text = response.message

                        dialogBinding.view.setOnClickListener {
                            dialogNoAuthentication?.dismiss()
                        }
                        dialogBinding.textOk.setOnClickListener {
                            dialogNoAuthentication?.dismiss()
                        }
                        build.setView(dialogBinding.root)
                        dialogNoAuthentication = build.create()
                        dialogNoAuthentication?.show()
                    }
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