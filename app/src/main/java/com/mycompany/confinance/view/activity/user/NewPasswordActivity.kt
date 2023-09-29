package com.mycompany.confinance.view.activity.user

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityNewPasswordBinding
import com.mycompany.confinance.databinding.CustomDialogNoConnectionLoginCreateAccountBinding
import com.mycompany.confinance.databinding.DialogCustomForgotPasswordBinding
import com.mycompany.confinance.util.Constants
import com.mycompany.confinance.viewmodel.user.NewPasswordViewModel

class NewPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewPasswordBinding
    private val viewModel: NewPasswordViewModel by viewModels()
    private var email: String? = null
    private var dialogNoConnection: AlertDialog? = null
    private var dialogNoAuthentication: AlertDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        email = intent.getStringExtra(Constants.KEY.KEY_EMAIL)
        handleClick()
        observe()
    }

    private fun observe() {
        viewModel.isLoading.observe(this) { loading ->
            if (loading) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                viewModel.error.observe(this) { response ->
                    if (response.code == 500) {
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
                    } else {
                        if (dialogNoAuthentication != null && dialogNoAuthentication?.isShowing == true) {
                            dialogNoAuthentication?.dismiss()
                        }

                        val build = AlertDialog.Builder(this, R.style.ThemeCustomDialogBottom)
                        val dialogBinding = DialogCustomForgotPasswordBinding.inflate(LayoutInflater.from(this))

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

    private fun handleClick() {
        binding.buttonContinue.setOnClickListener {
            val password = binding.editTextPassword.text.toString()
            val passwordConfirm = binding.editTextPasswordAgain.text.toString()

            viewModel.newPassword(email = email, password = password, passwordConfirm = passwordConfirm)
        }

        binding.imageEyeOne.setOnClickListener {
            val edit = binding.editTextPassword
            if (edit.transformationMethod == PasswordTransformationMethod.getInstance()) {
                edit.transformationMethod = null
                binding.imageEyeOne.setImageResource(R.drawable.olho_fechado)
            } else {
                edit.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.imageEyeOne.setImageResource(R.drawable.olho_aberto)
            }
        }

        binding.imageEyeTwo.setOnClickListener {
            val edit = binding.editTextPasswordAgain
            if (edit.transformationMethod == PasswordTransformationMethod.getInstance()) {
                edit.transformationMethod = null
                binding.imageEyeTwo.setImageResource(R.drawable.olho_fechado)
            } else {
                edit.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.imageEyeTwo.setImageResource(R.drawable.olho_aberto)
            }

        }

    }
}