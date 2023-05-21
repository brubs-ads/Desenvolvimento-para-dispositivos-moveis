package com.mycompany.confinance.view.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.controller.UserProfileController
import com.mycompany.confinance.databinding.ActivityUserProfileBinding
import com.mycompany.confinance.model.user.ResponseUserModel

class UserProfileActivity : AppCompatActivity() {
    private var userdata = ResponseUserModel()
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
        binding.edittextName.text.toString().let {
            userdata.getName()
        }
        binding.edittextEmail.text.toString().let {
            userdata.getEmail()
        }
    }
}