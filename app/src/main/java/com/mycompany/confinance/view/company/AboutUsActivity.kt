package com.mycompany.confinance.view.company

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mycompany.confinance.databinding.ActivityAboutUsBinding
import com.mycompany.confinance.view.main.MenuActivity

class AboutUsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutUsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutUsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        handleClick()
    }

    private fun handleClick() {
        binding.imageArrowBackAboutUs.setOnClickListener {
            startActivity(Intent(this,MenuActivity::class.java))
            finish()
        }
    }

}