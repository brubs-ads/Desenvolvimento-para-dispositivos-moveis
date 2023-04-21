package br.com.confinance.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.confinance.databinding.ActivityCreateAccountBinding

class CreateAccountActivity : AppCompatActivity() {
   private lateinit var binding: ActivityCreateAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
    }
}