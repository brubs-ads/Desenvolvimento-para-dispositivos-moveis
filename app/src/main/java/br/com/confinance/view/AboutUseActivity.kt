package br.com.confinance.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.confinance.databinding.ActivityAboutUseBinding

class AboutUseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutUseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutUseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
    }
}