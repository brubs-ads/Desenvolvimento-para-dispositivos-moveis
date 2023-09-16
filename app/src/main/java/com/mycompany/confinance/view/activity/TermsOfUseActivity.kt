package com.mycompany.confinance.view.activity

import android.graphics.fonts.FontFamily
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.TypefaceSpan
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.R.*
import com.mycompany.confinance.databinding.ActivityTermsOfUseBinding


class TermsOfUseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTermsOfUseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsOfUseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleClick()
    }

    private fun handleClick() {
        binding.imgBack.setOnClickListener {
            finish()
        }
    }


}