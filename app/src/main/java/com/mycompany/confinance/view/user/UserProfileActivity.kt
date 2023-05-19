package com.mycompany.confinance.view.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityUserProfileBinding

class UserProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}