package com.mycompany.confinance.view.activity.user

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.databinding.ActivityCodeForgotPasswordBinding
import com.mycompany.confinance.viewmodel.user.CodeForgotPasswordViewModel

class CodeForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCodeForgotPasswordBinding
    private val viewModel: CodeForgotPasswordViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCodeForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setEditTextListeners()
        handleEmailForwarding()
        handleClick()
        observe()
    }

    private fun handleEmailForwarding() {

        val textViewCountdown = binding.textResendTheEmailWithInstructions

        val totalTimeMillis = 3 * 60 * 1000

       val countDownTimer = object : CountDownTimer(totalTimeMillis.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                val countdownText = "Para reenviar o e-mail com instruções para “------------------” em ${seconds}s."

                runOnUiThread {
                    textViewCountdown.text = countdownText
                }
            }

            override fun onFinish() {
                textViewCountdown.text = "Tempo encerrado!"
            }
        }

        countDownTimer.start()


    }

    private fun observe() {
        viewModel.isLoading.observe(this) { it ->
            if (it) {
                startActivity(Intent(this, NewPasswordActivity::class.java))
                finish()
            } else {
                viewModel.error.observe(this) {
                    if (it.code == 500) {

                    } else {

                    }
                }
            }
        }
    }

    private fun handleClick() {
        binding.imageBack.setOnClickListener {
            finish()
        }

        binding.buttonContinue.setOnClickListener {

            val codeOne = binding.editCode1.text.toString()
            val codeTwo = binding.editCode2.text.toString()
            val codeTree = binding.editCode3.text.toString()
            val codeFour = binding.editCode4.text.toString()

            viewModel.reviewCode(
                codeOne = codeOne,
                codeTwo = codeTwo, codeTree = codeTree, codeFour = codeFour
            )
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