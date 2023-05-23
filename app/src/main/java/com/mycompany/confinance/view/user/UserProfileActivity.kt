package com.mycompany.confinance.view.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.controller.UserProfileController
import com.mycompany.confinance.databinding.ActivityUserProfileBinding

class UserProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserProfileBinding
    private val controller = UserProfileController()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleUser()
    }

    private fun handleUser() {
        controller.getUser()
    }
}