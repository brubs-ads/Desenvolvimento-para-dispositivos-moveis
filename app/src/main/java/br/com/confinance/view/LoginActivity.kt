package br.com.confinance.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.confinance.databinding.LoginActivityBinding
import br.com.confinance.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: LoginActivityBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        setContentView(binding.root)

        supportActionBar?.hide()
    }



}