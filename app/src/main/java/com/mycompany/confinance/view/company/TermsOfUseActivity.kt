package com.mycompany.confinance.view.company

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.databinding.ActivityTermsOfUseBinding
import com.mycompany.confinance.util.Constants
import com.mycompany.confinance.view.main.MenuActivity
import com.mycompany.confinance.view.user.CreateAccountActivity
import com.mycompany.confinance.view.user.LoginActivity

class TermsOfUseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTermsOfUseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsOfUseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        supportActionBar?.hide()

        val account = intent.getBooleanExtra(Constants.REDIRECTION.KEY.ACCOUNT, false)
        val login = intent.getBooleanExtra(Constants.REDIRECTION.KEY.LOGIN, false)
        val menu = intent.getBooleanExtra(Constants.REDIRECTION.KEY.MENU, false)

        handleClick(account = account, login = login, menu = menu)

    }

    private fun handleClick(account: Boolean, login: Boolean, menu: Boolean) {
        binding.imageArrowBackTermsOfUse.setOnClickListener {
            if (account) {
                startActivity(Intent(this, CreateAccountActivity::class.java))
                finish()
            } else if (login) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else if (menu) {
                startActivity(Intent(this, MenuActivity::class.java))
                finish()
            }
        }
    }
}