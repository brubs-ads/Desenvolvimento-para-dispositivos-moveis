package br.com.confinance.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.confinance.databinding.ActivityCreateAccountBinding

class CreateAccount : AppCompatActivity(){
    private lateinit var binding: ActivityCreateAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        createAccount()
    }

   private fun createAccount(){
      val name = binding.editName.text.toString()
      val email = binding.editEmail.text.toString()
      val password =  binding.editPassword.text.toString()
   }


}