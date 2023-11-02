package com.mycompany.confinance.view.activity.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.databinding.ActivityUserProfileBinding
import com.mycompany.confinance.model.UserModel
import com.mycompany.confinance.view.activity.MainActivity
import com.mycompany.confinance.viewmodel.user.UserProfileViewModel

class UserProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserProfileBinding
    private val viewModel: UserProfileViewModel by viewModels()
    private lateinit var user: UserModel
    private var isEditNameEnabled = false
    private var isEditEmailEnabled = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getUser()
        observe()
        handleClick()
    }

    private fun observe() {
        viewModel.user.observe(this) {
            user = it
            binding.editName.setText(user.name)
            binding.editEmail.setText(user.email)
        }

        viewModel.isLoadingUpdate.observe(this) {
            if (it) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "ocorreu algun erro", Toast.LENGTH_LONG).show()
            }
        }
        viewModel.resultDeleteUser.observe(this){
            if (it){
                startActivity(Intent(this,CreateAccountActivity::class.java))
                finish()
            }else{
                Toast.makeText(this, "ocorreu algun erro", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun handleClick() {
        binding.arrowBack.setOnClickListener {
            finish()
        }

        binding.buttonDelete.setOnClickListener {
            viewModel.deleteUser()

        }

        binding.imageUpdateTextName.setOnClickListener {
            if (isEditNameEnabled) {
                binding.editName.text!!.clear()
                binding.editName.text!!.append(user.name)
                binding.editName.isEnabled = false
                binding.editName.clearFocus()
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.editName.windowToken, 0)
            } else {
                binding.editName.isEnabled = true
                binding.editName.isFocusableInTouchMode = true
                binding.editName.requestFocus()
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(binding.editName, InputMethodManager.SHOW_IMPLICIT)
            }
            isEditNameEnabled = !isEditNameEnabled
        }

        binding.editName.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.editName.isEnabled = false
                isEditNameEnabled = false
                true
            } else {
                false
            }
        }

        binding.imageUptadeTextEmail.setOnClickListener {
            if (isEditEmailEnabled) {
                binding.editEmail.text!!.clear()
                binding.editEmail.text!!.append(user.email)
                binding.editEmail.isEnabled = false
                binding.editEmail.clearFocus()
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.editEmail.windowToken, 0)
            } else {
                binding.editEmail.isEnabled = true
                binding.editEmail.isFocusableInTouchMode = true
                binding.editEmail.requestFocus()
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(binding.editEmail, InputMethodManager.SHOW_IMPLICIT)
            }
            isEditEmailEnabled = !isEditEmailEnabled
        }

        binding.editEmail.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.editEmail.isEnabled = false
                isEditEmailEnabled = false
                true
            } else {
                false
            }
        }

        binding.imageConfirm.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val name = binding.editName.text.toString()
            viewModel.updateNameAndEmail(email, name, user)
        }
    }

}