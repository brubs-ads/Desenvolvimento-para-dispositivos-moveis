package com.mycompany.confinance.view.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.databinding.ActivityMenuBinding
import com.mycompany.confinance.util.Constants
import com.mycompany.confinance.view.company.AboutUsActivity
import com.mycompany.confinance.view.company.TermsOfUseActivity
import com.mycompany.confinance.view.expense.ExpensesActivity
import com.mycompany.confinance.view.objective.ObjectiveActivity
import com.mycompany.confinance.view.revenue.RevenuesActivity
import com.mycompany.confinance.view.user.UserProfileActivity

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleClick()

    }

    private fun handleClick() {
        binding.textRevenues.setOnClickListener {
            startActivity(Intent(this, RevenuesActivity::class.java))
            finish()
        }

        binding.textInitial.setOnClickListener{
            startActivity(Intent(this,InitialActivity::class.java))
            finish()
        }

        binding.textExpenses.setOnClickListener {
            startActivity(Intent(this, ExpensesActivity::class.java))
            finish()
        }

        binding.textObjective.setOnClickListener {
            startActivity(Intent(this, ObjectiveActivity::class.java))
            finish()
        }

        binding.textAbout.setOnClickListener {
            startActivity(Intent(this, AboutUsActivity::class.java))
            finish()
        }

        binding.textTermsOfUse.setOnClickListener {
            val intent = Intent(this, TermsOfUseActivity::class.java)
            intent.putExtra(Constants.REDIRECTION.KEY.MENU, Constants.REDIRECTION.VALUE.MENU)
            startActivity(intent)
            finish()
        }

        binding.imageProfilePicture.setOnClickListener {
            startActivity(Intent(this, UserProfileActivity::class.java))
            finish()
        }
    }
}




