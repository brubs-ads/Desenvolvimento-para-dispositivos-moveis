package com.mycompany.confinance.view.activity.user

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.databinding.ActivityUserProfileBinding
import com.mycompany.confinance.viewmodel.user.UserProfileViewModel

class UserProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserProfileBinding
    private val viewModel: UserProfileViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getUser()
        observe()
        handleClick()
    }

    private fun observe() {
        viewModel.isLoading.observe(this){
            if (it){
                startActivity(Intent(this,CreateAccountActivity::class.java))
                finish()
            }else{

            }
        }
    }

    private fun handleClick() {
        binding.buttonDelete.setOnClickListener {
//            viewModel.deleteUser()
        }

        binding.editTextName.setOnClickListener {
            binding.editName.isFocusable = true
            binding.editName.isFocusableInTouchMode = true
            binding.editName.requestFocus()

        }

        binding.editTextEmail.setOnClickListener {
            binding.editEmail.isFocusable = true
            binding.editEmail.isFocusableInTouchMode = true
            binding.editEmail.requestFocus()
        }

    }


}