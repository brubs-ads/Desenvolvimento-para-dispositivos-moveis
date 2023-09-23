package com.mycompany.confinance.view.activity.user

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.method.PasswordTransformationMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityCreateAccountBinding
import com.mycompany.confinance.databinding.CustomDialogLoginAuthenticationBinding
import com.mycompany.confinance.databinding.CustomDialogNoConnectionLoginCreateAccountBinding
import com.mycompany.confinance.view.activity.MainActivity
import com.mycompany.confinance.view.company.PrivacyActivity
import com.mycompany.confinance.view.company.TermsOfUseActivity
import com.mycompany.confinance.viewmodel.user.CreateAccountViewModel

class CreateAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAccountBinding
    private var dialogNoConnection : AlertDialog? = null
    private var dialogNoAuthentication : AlertDialog? = null
    private val viewModel: CreateAccountViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleCreateAccount()
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

    private fun handleClick() {
        binding.textHaveAccount.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.imageEye.setOnClickListener {
            val edit = binding.editPasswordCreateAccount
            if (edit.transformationMethod == PasswordTransformationMethod.getInstance()) {
                edit.transformationMethod = null
                binding.imageEye.setImageResource(R.drawable.olho_fechado)
            } else {
                edit.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.imageEye.setImageResource(R.drawable.olho_aberto)
            }

        }
    }

    private fun handleCreateAccount() {
        binding.buttonCreateAccount.setOnClickListener {
            val name = binding.editName.text.toString()
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
            }
            else {
                viewModel.error.observe(this){ response ->
                    when (response.code) {
                        500 -> {
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
                        else -> {
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
    }
}
