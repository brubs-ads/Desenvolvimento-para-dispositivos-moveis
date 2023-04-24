package br.com.confinance.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.confinance.R
import br.com.confinance.databinding.ActivityTermsOfUseBinding

class TermsOfUseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTermsOfUseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsOfUseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.imageArrowBackTermsOfUse.setOnClickListener {
            finish()
        }
    }



}