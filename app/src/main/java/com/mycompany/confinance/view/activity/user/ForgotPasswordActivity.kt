package com.mycompany.confinance.view.activity.user

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityForgotPasswordBinding
import com.mycompany.confinance.databinding.CustomDialogNoConnectionLoginCreateAccountBinding
import com.mycompany.confinance.databinding.DialogCustomForgotPasswordBinding
import com.mycompany.confinance.viewmodel.user.ForgotPasswordViewModel

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    private val viewModel: ForgotPasswordViewModel by viewModels()
    private var dialogNoConnection:AlertDialog? = null
    private var dialogNoAuthentication:AlertDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleClick()
        observe()

    }

    private fun handleClick() {
        binding.buttonContinue.setOnClickListener {
            handleCredential()
        }
    }

    private fun observe() {
        viewModel.isLoading.observe(this) { loading ->
            if (loading) {
                val email = binding.editEmailForgot.text.toString()
                startActivity(Intent(this, CodeForgotPasswordActivity::class.java))
            }
            else {
                viewModel.error.observe(this) { response ->
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
                            val dialogBinding  = DialogCustomForgotPasswordBinding.inflate(LayoutInflater.from(this))

                            dialogBinding.textError.text = response.message

                            dialogBinding.textTryAgain.setOnClickListener {
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

    private fun handleCredential() {
        val email = binding.editEmailForgot.text.toString()
        viewModel.forgotPassword(email = email)
    }
}