package com.mycompany.confinance.view.activity.user

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.databinding.ActivityNewPasswordBinding
import com.mycompany.confinance.viewmodel.user.NewPasswordViewModel

class NewPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewPasswordBinding
    private val viewModel: NewPasswordViewModel by viewModels()
    private var email : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        email = intent.getStringExtra("email")
        handleClick()
    }

    private fun handleClick() {
        binding.buttonContinue.setOnClickListener {
            val password = binding.editTextPassword.text.toString()
            val passwordConfirm = binding.editTextPasswordAgain.text.toString()

            viewModel.newPassword(password = password, passwordConfirm = passwordConfirm)

        }
    }
}