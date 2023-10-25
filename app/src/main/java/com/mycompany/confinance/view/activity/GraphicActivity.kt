package com.mycompany.confinance.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.databinding.ActivityGraphicBinding

class GraphicActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGraphicBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGraphicBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}