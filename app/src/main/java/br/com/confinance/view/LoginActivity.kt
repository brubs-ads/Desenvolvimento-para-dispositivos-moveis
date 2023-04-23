package br.com.confinance.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.confinance.databinding.LoginActivityBinding

class LoginActivity:AppCompatActivity() {
    private lateinit var binding: LoginActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
    }


}