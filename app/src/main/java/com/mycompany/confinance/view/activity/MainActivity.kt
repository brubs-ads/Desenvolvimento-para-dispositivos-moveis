package com.mycompany.confinance.view.activity

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.TextAppearanceSpan
import androidx.activity.viewModels
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityMainBinding
import com.mycompany.confinance.viewmodel.HomeViewModel
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val viewModel : HomeViewModel by viewModels()
    private val calendar = Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        navView.itemIconTintList = null
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        styleFontMenu(navView)

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_renevue -> {
                    startActivity(Intent(this, CreateRevenueActivity::class.java))
                    true
                }

                else -> false
            }
        }

        updateMonthYearText()
        checkMonthAndYear()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun styleFontMenu(navView: NavigationView) {
        val myMenu = navView.menu
        val planItem = myMenu.findItem(R.id.plan)
        val sPlan = SpannableString(planItem.title)
        sPlan.setSpan(
            TextAppearanceSpan(this, R.style.NavigationItemAppearance),
            0,
            sPlan.length,
            0
        )
        planItem.title = sPlan

        val analyzeItem = myMenu.findItem(R.id.analyze)
        val sAnalyze = SpannableString(analyzeItem.title)
        sAnalyze.setSpan(
            TextAppearanceSpan(this, R.style.NavigationItemAppearance),
            0,
            sAnalyze.length,
            0
        )
        analyzeItem.title = sAnalyze


        val homeItem = myMenu.findItem(R.id.nav_home)
        val sHome = SpannableString(homeItem.title)
        sHome.setSpan(
            TextAppearanceSpan(this, R.style.NavigationSubTitleAppearance),
            0,
            sHome.length,
            0
        )
        homeItem.title = sHome


        val revenueItem = myMenu.findItem(R.id.nav_renevue)
        val sRevenue = SpannableString(revenueItem.title)
        sRevenue.setSpan(
            TextAppearanceSpan(this, R.style.NavigationSubTitleAppearance),
            0,
            sRevenue.length,
            0
        )
        revenueItem.title = sRevenue

        val expenseItem = myMenu.findItem(R.id.nav_expense)
        val sExpense = SpannableString(expenseItem.title)
        sExpense.setSpan(
            TextAppearanceSpan(this, R.style.NavigationSubTitleAppearance),
            0,
            sExpense.length,
            0
        )
        expenseItem.title = sExpense

        val graphicItem = myMenu.findItem(R.id.nav_graphic)
        val sgraphic = SpannableString(graphicItem.title)
        sgraphic.setSpan(
            TextAppearanceSpan(this, R.style.NavigationSubTitleAppearance),
            0,
            sgraphic.length,
            0
        )
        graphicItem.title = sgraphic

        val aboutItem = myMenu.findItem(R.id.nav_about_us)
        val sAbout = SpannableString(aboutItem.title)
        sAbout.setSpan(
            TextAppearanceSpan(this, R.style.NavigationSubTitleAppearance),
            0,
            sAbout.length,
            0
        )
        aboutItem.title = sAbout

        val termsItem = myMenu.findItem(R.id.nav_terms_of_use)
        val sTerms = SpannableString(termsItem.title)
        sTerms.setSpan(
            TextAppearanceSpan(this, R.style.NavigationSubTitleAppearance),
            0,
            sTerms.length,
            0
        )
        termsItem.title = sTerms

        val exitItem = myMenu.findItem(R.id.nav_exit)
        val sExit = SpannableString(exitItem.title)
        sExit.setSpan(
            TextAppearanceSpan(this, R.style.NavigationSubTitleAppearance),
            0,
            sExit.length,
            0
        )
        exitItem.title = sExit

    }

    private fun checkMonthAndYear() {
        binding.appBarMain.arrowBack.setOnClickListener {
            if (calendar.get(Calendar.MONTH) == Calendar.JANUARY) {
                calendar.add(Calendar.YEAR, -1)
                calendar.set(Calendar.MONTH, Calendar.DECEMBER)
            } else {
                calendar.add(Calendar.MONTH, -1)
            }
            updateMonthYearText()
            handleQuery()
        }

        binding.appBarMain.arrowNext.setOnClickListener {
            if (calendar.get(Calendar.MONTH) == Calendar.DECEMBER) {
                calendar.add(Calendar.YEAR, 1)
                calendar.set(Calendar.MONTH, Calendar.JANUARY)
            } else {
                calendar.add(Calendar.MONTH, 1)
            }
            updateMonthYearText()
            handleQuery()
        }
    }


    private fun updateMonthYearText() {
        val currentDate = Calendar.getInstance()
        val currentYear = currentDate.get(Calendar.YEAR)

        val displayedYear = calendar.get(Calendar.YEAR)
        val displayedMonth = calendar.get(Calendar.MONTH)

        val monthArray = resources.getStringArray(R.array.months)
        val monthAbbreviationsArray = resources.getStringArray(R.array.month_abbreviations)

        val formattedDate = if (displayedYear != currentYear) {
            "${monthAbbreviationsArray[displayedMonth]}/${displayedYear % 100}"
        } else {
            monthArray[displayedMonth]
        }


        binding.appBarMain.textMonth.text = formattedDate
        handleQuery()
    }

    private fun handleQuery() {
        val displayedDate = binding.appBarMain.textMonth.text.toString()
        if (displayedDate.isNotEmpty()) {
            val monthAbbreviationsArray = resources.getStringArray(R.array.month_abbreviations)
            val currentYear = Calendar.getInstance().get(Calendar.YEAR)

            val monthIndex = monthAbbreviationsArray.indexOfFirst {
                it.equals(displayedDate.substring(0, 3), ignoreCase = true)
            }

            if (monthIndex >= 0) {
                val yearString = if (displayedDate.length == 7) {
                    displayedDate.substring(4)
                } else {
                    currentYear.toString()
                }

                val year = try {
                    yearString.toInt()
                } catch (e: NumberFormatException) {
                    currentYear
                }

                val month = monthIndex + 1
                val yearatt = year
            }
        }
    }

}