package com.mycompany.confinance.view.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuInflater
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityCreateRevenueBinding
import com.mycompany.confinance.databinding.CustomBottomSheetBinding
import com.mycompany.confinance.util.DatePickerFragment
import com.mycompany.confinance.viewmodel.CreateRevenueViewModel

class CreateRevenueActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateRevenueBinding
    private lateinit var sheetBinding: CustomBottomSheetBinding
    private val viewModel: CreateRevenueViewModel by viewModels()
    private var selectedCardView: Int? = null
    private var switchState = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateRevenueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleDate()
        handleClick()
        handleRepetition()
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
        binding.switchRevenue.setOnCheckedChangeListener { _, isChecked ->
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
            val datePicker = DatePickerFragment { day, month, year -> onDateSelect(day, month, year) }
            datePicker.show(supportFragmentManager, "datePicker")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun onDateSelect(day: Int, mounth: Int, year: Int) {
        binding.textData.text = "$day/$mounth/$year"
    }


    fun save() {
        binding.buttonCreate.setOnClickListener {
            val value = binding.editBalanceRevenue.text.toString().toLong()
            val description = binding.editTextDescription.text.toString()
            val date = binding.textData.text.toString()
            val fixed = binding.switchRevenue.isChecked
            val repetition = binding.textRepetition.text.toString()
            val category = selectedCardView

            viewModel.createRevenue(
                value,
                description = description,
                data = date,
                fixedIncome = fixed,
                repetitions = repetition,
                category = category!!
            )
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun handleCategory() {
        val cardViews = listOf(
            binding.cardSalary,
            binding.cardInvesti,
            binding.cardService,
            binding.cardOutro
        )

        for (cardView in cardViews) {
            cardView.setOnClickListener {
                when (cardView.id) {
                    R.id.card_salary -> {
                        selectedCardView = 1
                    }

                    R.id.card_investi -> {
                        selectedCardView = 2
                    }

                    R.id.card_service -> {
                        selectedCardView = 3
                    }

                    R.id.card_outro -> {
                        selectedCardView = 4

                    }
                }

            }
        }

    }


}

