package br.com.confinance.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.confinance.MainActivity
import br.com.confinance.R
import br.com.confinance.databinding.ActivityCreateAccountBinding
import br.com.confinance.viewmodel.CreateAccountViewModel

class CreateAccountActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityCreateAccountBinding
    private lateinit var viewModel: CreateAccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[CreateAccountViewModel::class.java]
        setContentView(binding.root)


        supportActionBar?.hide()

        onAction()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_account -> {
                handleLogin()
            }

            R.id.text_terms_of_use -> {
                startActivity(Intent(this, TermsOfUseActivity::class.java))
            }

            R.id.text_privacy_polices -> {
                TODO("Não tem, ainda tô fazendo")
            }
            R.id.image_arrow_create_account ->{
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

    private fun onAction() {
        binding.buttonAccount.setOnClickListener(this)
        binding.textTermsOfUse.setOnClickListener(this)
        binding.textPrivacyPolices.setOnClickListener(this)
        binding.imageArrowCreateAccount.setOnClickListener(this)
    }

    private fun handleLogin() {
        val email = binding.editEmail.text.toString()
        val password = binding.editEmail.text.toString()

        viewModel.dologin(email, password)
    }
}

