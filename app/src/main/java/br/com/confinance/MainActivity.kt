package br.com.confinance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.confinance.databinding.ActivityMainBinding
import br.com.confinance.view.CreateAccountActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        userAction()

    }

    private fun userAction() {
        binding.buttonCreateAccount.setOnClickListener {
                startActivity(Intent(this,CreateAccountActivity::class.java))
        }
        binding.textLogin.setOnClickListener {
            /*startActivity()*/
        }

    }


}