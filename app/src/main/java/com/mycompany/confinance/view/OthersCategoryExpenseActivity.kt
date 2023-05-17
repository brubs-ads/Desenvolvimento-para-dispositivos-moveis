package com.mycompany.confinance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mycompany.confinance.databinding.ActivityOthersCategoryExpenseBinding

class OthersCategoryExpenseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOthersCategoryExpenseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOthersCategoryExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}