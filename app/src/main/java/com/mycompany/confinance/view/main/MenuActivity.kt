package com.mycompany.confinance.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mycompany.confinance.databinding.ActivityMenuBinding
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

        handleclick()
    }

    private fun handleclick() {
        binding.textRevenues.setOnClickListener {
        val revenuesintent = Intent(this, RevenuesActivity::class.java)
        startActivity(revenuesintent)

        binding.textExpenses.setOnClickListener {
        val expensesintent = Intent(this, ExpensesActivity::class.java)
        startActivity(expensesintent)

        binding.textObjective.setOnClickListener {
        val objectiveintent = Intent(this, ObjectiveActivity::class.java)
        startActivity(objectiveintent)

        binding.textAbout.setOnClickListener {
        val aboutintent = Intent(this, AboutUsActivity::class.java)
        startActivity(aboutintent)

        binding.textTermsOfUse.setOnClickListener {
        val termsofuseintent = Intent(this, TermsOfUseActivity::class.java)
        startActivity(termsofuseintent)

        binding.imageProfilePicture.setOnClickListener{
        val profilepictureintent = Intent(this,UserProfileActivity::class.java)
        startActivity(profilepictureintent)

          }
         }
       }
       }
      }
     }
  }
}
