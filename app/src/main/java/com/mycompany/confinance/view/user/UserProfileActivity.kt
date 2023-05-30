package com.mycompany.confinance.view.user

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.MainActivity
import com.mycompany.confinance.controller.UserProfileController
import com.mycompany.confinance.databinding.ActivityUserProfileBinding
import com.mycompany.confinance.view.main.InitialActivity

class UserProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserProfileBinding
    private val controller = UserProfileController()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        handleClick()
        handleUser()
    }

    private fun handleClick() {
        binding.imageBackProfile.setOnClickListener {
            startActivity(Intent(this,InitialActivity::class.java))
            finish()
        }
        binding.buttonExit.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
        binding.buttonDelete.setOnClickListener {
            deleteUser()
        }
    }

    private fun handleUser() {
        controller.getUser(onSuccess = { name, email ->
            binding.edittextName.setText(name)
            binding.edittextEmail.setText(email)
        }, onFailure = {message ->
            Toast.makeText(this,message,Toast.LENGTH_LONG).show()
        })
    }

    private fun deleteUser() {
        controller.deleteUser(result = { message, status ->
            if (status){
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else{
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
