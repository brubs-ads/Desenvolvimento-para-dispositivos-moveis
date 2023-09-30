package com.mycompany.confinance.view.activity.user

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityCodeForgotPasswordBinding
import com.mycompany.confinance.databinding.CustomDialogNoConnectionLoginCreateAccountBinding
import com.mycompany.confinance.databinding.DialogCustomCodeForgotPasswordBinding
import com.mycompany.confinance.util.Constants
import com.mycompany.confinance.viewmodel.user.CodeForgotPasswordViewModel

class CodeForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCodeForgotPasswordBinding
    private val viewModel: CodeForgotPasswordViewModel by viewModels()
    private var email: String? = null
    private var dialogNoConnection: AlertDialog? = null
    private var dialogNoAuthentication: AlertDialog? = null
    private var button: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCodeForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        email = intent.getStringExtra(Constants.KEY.KEY_EMAIL)

        setEditTextListeners()
        handleEmailForwarding()
        handleClick()
        observe()
    }

    private fun maskEmail(email: String?): String {
        return if (email?.isNotEmpty()!! && email.contains("@")) {
            val arroba = email.indexOf('@')
            val visiblePart = email.substring(0, minOf(3, arroba))
            val maskPart = "x".repeat(arroba - minOf(3, arroba))
            val domain = email.substring(arroba)
            visiblePart + maskPart + domain
        } else {
            email
        }
    }

    private fun handleEmailForwarding() {

        val textViewCountdown = binding.textResendTheEmailWithInstructions

        val totalTimeMillis = 3 * 60 * 1000

        val countDownTimer = object : CountDownTimer(totalTimeMillis.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                val countdownText =
                    "Para reenviar o e-mail com instruções para ${maskEmail(email)} em ${seconds}s."
                runOnUiThread {
                    textViewCountdown.text = countdownText
                }
            }

            override fun onFinish() {
                textViewCountdown.text = getString(R.string.time_ended)
                button = true
            }
        }

        countDownTimer.start()


    }

    private fun observe() {
        viewModel.isLoading.observe(this) { loading ->
            if (loading) {
                val intent = Intent(this, NewPasswordActivity::class.java)
                intent.putExtra(Constants.KEY.KEY_EMAIL,email)
                startActivity(intent)
                finish()
            } else {
                viewModel.error.observe(this) { response ->
                    if (response.code == 500) {
                        if (dialogNoConnection != null && dialogNoConnection?.isShowing == true) {
                            dialogNoConnection?.dismiss()
                        }

                        val build = AlertDialog.Builder(this, R.style.ThemeCustomDialog)
                        val dialogBinding = CustomDialogNoConnectionLoginCreateAccountBinding.inflate(
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
                        val dialogBinding = DialogCustomCodeForgotPasswordBinding.inflate(LayoutInflater.from(this))

                        dialogBinding.textInformation.text = response.message

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
        binding.imageBack.setOnClickListener {
            startActivity(Intent(this,ForgotPasswordActivity::class.java))
            finish()
        }

        binding.buttonContinue.setOnClickListener {

            val codeOne = binding.editCode1.text.toString()
            val codeTwo = binding.editCode2.text.toString()
            val codeTree = binding.editCode3.text.toString()
            val codeFour = binding.editCode4.text.toString()

            viewModel.reviewCode(
                email = email,
                codeOne = codeOne, codeTwo = codeTwo, codeTree = codeTree, codeFour = codeFour
            )
        }

        binding.buttonSendEmailAgain.setOnClickListener {
            if (button) {
                viewModel.forgotPassword(email = email)
            } else {
                Toast.makeText(applicationContext, getString(R.string.wait_for_the_time_to_end), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setEditTextListeners() {
        val editTextList = listOf(
            binding.editCode1, binding.editCode2, binding.editCode3, binding.editCode4
        )

        for (i in editTextList.indices) {
            val editText = editTextList[i]

            editText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.isNotEmpty() == true) {
                        if (i < editTextList.size - 1) {
                            editTextList[i + 1].requestFocus()
                        } else if (i == editTextList.size - 1 && s.length > 1) {
                            editText.text?.clear()
                        }
                    }
                }
            })

            editText.setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_UP) {
                    if (i > 0) {
                        editTextList[i - 1].requestFocus()
                    }
                }
                false
            }
        }
    }


}