package com.mycompany.confinance.view.activity.expense

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.PopupMenu
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityCreateExpenseBinding
import com.mycompany.confinance.databinding.CustomBottomSheetBinding
import com.mycompany.confinance.util.DatePickerFragment
import com.mycompany.confinance.viewmodel.CreateExpenseViewModel
import com.mycompany.confinance.viewmodel.CreateRevenueViewModel

class CreateExpenseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateExpenseBinding
    private lateinit var sheetBinding: CustomBottomSheetBinding
    private val viewModel: CreateExpenseViewModel by viewModels()
    private var selectedCardView: Int? = null
    private var switchState = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)


        handleDate()
        handleClick()
        handleRepetition()
        observe()
    }

    private fun observe() {
        viewModel.isLoading.observe(this) {
            if (it) {
                startActivity(Intent(this, ExpenseActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "erro", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun handleClick() {
        binding.arrowClose.setOnClickListener {
            finish()
        }

        handleCategory()

        binding.buttonCreate.setOnClickListener {
            save()
        }
    }

    private fun handleRepetition() {
        binding.switchExpense.setOnCheckedChangeListener { _, isChecked ->
            switchState = isChecked
        }

        binding.textRepetition.setOnClickListener {
            if (!switchState) {
                openBottomSheet()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun openBottomSheet() {
        var cont: String? = null
        var period: String? = null
        val dialog = BottomSheetDialog(this)
        sheetBinding =
            CustomBottomSheetBinding.inflate(
                layoutInflater, null, false
            )
        dialog.setContentView(sheetBinding.root)

        sheetBinding.button.setBackgroundResource(R.drawable.button_custom_sheet_expense)

        if (cont != null && period != null) {
            sheetBinding.textCont.text = cont
            sheetBinding.textPeriodTotal.text = period
        }

        sheetBinding.arrowTop.setOnClickListener {
            val currentValue = sheetBinding.textCont.text.toString().toInt()
            val newValue = currentValue + 1
            sheetBinding.textCont.text = newValue.toString()
        }
        sheetBinding.arrowBottom.setOnClickListener {
            val currentValue = sheetBinding.textCont.text.toString().toInt()
            val newValue = currentValue - 1
            sheetBinding.textCont.text = newValue.toString()
        }

        sheetBinding.arrowBottomMenu.setOnClickListener {
            showPopupMenu(it)
        }

        sheetBinding.button.setOnClickListener {
            cont = sheetBinding.textCont.text.toString()
            period = sheetBinding.textPeriodTotal.text.toString()
            binding.textRepetition.text = "${cont}x $period"
            dialog.dismiss()
        }

        dialog.show()
    }


    private fun showPopupMenu(view: View) {
        val popup = PopupMenu(this, view)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.menu_popup, popup.menu)
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.monthly -> {
                    sheetBinding.textPeriodTotal.text = applicationContext.getString(R.string.monthly)
                    return@setOnMenuItemClickListener true
                }

                R.id.daily -> {
                    sheetBinding.textPeriodTotal.text = applicationContext.getString(R.string.daily)
                    return@setOnMenuItemClickListener true
                }

                R.id.weekly -> {
                    sheetBinding.textPeriodTotal.text = applicationContext.getString(R.string.weekly)
                    return@setOnMenuItemClickListener true
                }

                R.id.annual -> {
                    sheetBinding.textPeriodTotal.text = applicationContext.getString(R.string.annual)
                    return@setOnMenuItemClickListener true
                }

                else -> return@setOnMenuItemClickListener false
            }
        }

        popup.show()
    }

    private fun handleDate() {
        binding.textData.setOnClickListener {
            val datePicker = DatePickerFragment { day, month, year -> onDateSelect(day, month, year)}
            datePicker.setStyle(R.style.DatePickExpense)
            datePicker.show(supportFragmentManager, "datePicker")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun onDateSelect(day: Int, mounth: Int, year: Int) {
        val mounthNew = mounth+1
        val formattedDay = if (day <= 9) "0$day" else "$day"
        val formattedMonth = if (mounthNew <= 9) "0$mounthNew" else "$mounthNew"
        val formattedDate = "$formattedDay/$formattedMonth/$year"
        binding.textData.text = formattedDate
    }


    private fun save() {
        val value = binding.editBalanceExpense.cleanDoubleValue
        val description = binding.editTextDescription.text.toString()
        val date = binding.textData.text.toString()
        val fixed = binding.switchExpense.isChecked
        val repetition = binding.textRepetition.text.toString()
        val photo = selectedCardView

        viewModel.createExpense(
            value.toLong(),
            description = description,
            data = date,
            fixedIncome = fixed,
            repetitions = repetition,
            photo = photo!!
        )
    }

    @SuppressLint("ResourceAsColor")
    private fun handleCategory() {
        val cardViews = listOf(
            binding.cardFood,
            binding.cardEducation,
            binding.cardHealth,
            binding.cardOuther1,
            binding.cardHome
        )

        for (cardView in cardViews) {
            cardView.setOnClickListener {
                when (cardView.id) {
                    R.id.card_food -> {
                        selectedCardView = 1
                        binding.imgFood.setImageResource(R.drawable.alimen_vermelho)
                        binding.imgEducation.setImageResource(R.drawable.educa__o)
                        binding.imgHealth.setImageResource(R.drawable.saude)
                        binding.imgOuther.setImageResource(R.drawable.outros_1)
                        binding.imgHome.setImageResource(R.drawable.casa)
                    }

                    R.id.card_education -> {
                        selectedCardView = 2
                        binding.imgFood.setImageResource(R.drawable.alimenta__o)
                        binding.imgEducation.setImageResource(R.drawable.edu_vermelho)
                        binding.imgHealth.setImageResource(R.drawable.saude)
                        binding.imgOuther.setImageResource(R.drawable.outros_1)
                        binding.imgHome.setImageResource(R.drawable.casa)

                    }

                    R.id.card_health -> {
                        selectedCardView = 3
                        binding.imgFood.setImageResource(R.drawable.alimenta__o)
                        binding.imgEducation.setImageResource(R.drawable.educa__o)
                        binding.imgHealth.setImageResource(R.drawable.sude_vermelho)
                        binding.imgOuther.setImageResource(R.drawable.outros_1)
                        binding.imgHome.setImageResource(R.drawable.casa)

                    }

                    R.id.card_outher_1 -> {
                        selectedCardView = 4
                        binding.imgFood.setImageResource(R.drawable.alimenta__o)
                        binding.imgEducation.setImageResource(R.drawable.educa__o)
                        binding.imgHealth.setImageResource(R.drawable.saude)
                        binding.imgOuther.setImageResource(R.drawable.outro_vermelho)
                        binding.imgHome.setImageResource(R.drawable.casa)

                    }
                    R.id.card_home ->{
                        selectedCardView = 5
                        binding.imgFood.setImageResource(R.drawable.alimenta__o)
                        binding.imgEducation.setImageResource(R.drawable.educa__o)
                        binding.imgHealth.setImageResource(R.drawable.saude)
                        binding.imgOuther.setImageResource(R.drawable.outros_1)
                        binding.imgHome.setImageResource(R.drawable.casa_vermelha)
                    }
                }

            }
        }

    }

}