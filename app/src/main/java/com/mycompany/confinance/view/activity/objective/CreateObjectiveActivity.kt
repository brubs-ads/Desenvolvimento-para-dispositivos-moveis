package com.mycompany.confinance.view.activity.objective

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.R.*
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityCreateObjectiveBinding
import com.mycompany.confinance.util.DatePickerFragment

class CreateObjectiveActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateObjectiveBinding
    private var selectedCardView: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateObjectiveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleClick()
        handleCategory()

    }

    private fun handleClick() {
        binding.arrowClose.setOnClickListener {
            finish()
        }
        binding.buttonCreate.setOnClickListener {
            save()
        }
        binding.textData.setOnClickListener {
            val datePicker = DatePickerFragment { day, month, year -> onDateSelect(day, month, year) }
            datePicker.setStyle(R.style.DatePickObjective)
            datePicker.show(supportFragmentManager, "datePicker")
        }
    }

    private fun save() {
        val value = binding.editBalanceObjective.cleanDoubleValue
        val squared = binding.editSpared.cleanDoubleValue
        val descripton = binding.textNameObjective.text.toString()
        val date = binding.textData.text.toString()
    }

    @SuppressLint("SetTextI18n")
    private fun onDateSelect(day: Int, mounth: Int, year: Int) {
        val mounthNew = mounth + 1
        val formattedDay = if (day <= 9) "0$day" else "$day"
        val formattedMonth = if (mounthNew <= 9) "0$mounthNew" else "$mounthNew"
        val formattedDate = "$formattedDay/$formattedMonth/$year"
        binding.textData.text = formattedDate
    }

    @SuppressLint("ResourceAsColor")
    private fun handleCategory() {
        val views = listOf(
            binding.cardHealth1,
            binding.cardCell,
            binding.cardCar,
            binding.cardJob,
            binding.cardGift,
            binding.cardShopping,
            binding.cardCompras,
            binding.cardOuther2
        )

        for (view in views) {
            view.setOnClickListener {
                when (view.id) {
                    R.id.card_health_1 -> {
                        selectedCardView = 1
                        binding.cardHealth1.setBackgroundResource(R.drawable.background_rounded_card_objective)
                        binding.cardOuther2.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardCell.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardCar.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardGift.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardJob.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardCompras.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardShopping.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                    }

                    R.id.card_cell ->{
                        selectedCardView = 2
                        binding.cardCell.setBackgroundResource(R.drawable.background_rounded_card_objective)
                        binding.cardHealth1.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardOuther2.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardCar.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardGift.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardJob.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardCompras.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardShopping.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                    }

                    R.id.card_car ->{
                        selectedCardView = 3
                        binding.cardCar.setBackgroundResource(R.drawable.background_rounded_card_objective)
                        binding.cardCell.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardHealth1.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardOuther2.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardGift.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardJob.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardCompras.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardShopping.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                    }

                    R.id.card_job ->{
                        selectedCardView = 3
                        binding.cardJob.setBackgroundResource(R.drawable.background_rounded_card_objective)
                        binding.cardCell.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardHealth1.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardOuther2.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardCar.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardGift.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardCompras.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardShopping.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                    }

                    R.id.card_gift ->{
                        selectedCardView = 3
                        binding.cardGift.setBackgroundResource(R.drawable.background_rounded_card_objective)
                        binding.cardJob.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardCell.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardHealth1.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardOuther2.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardCar.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardCompras.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardShopping.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                    }

                    R.id.card_shopping ->{
                        selectedCardView = 3
                        binding.cardShopping.setBackgroundResource(R.drawable.background_rounded_card_objective)
                        binding.cardGift.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardJob.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardCell.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardHealth1.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardOuther2.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardCar.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardCompras.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                    }

                    R.id.card_compras ->{
                        selectedCardView = 3
                        binding.cardCompras.setBackgroundResource(R.drawable.background_rounded_card_objective)
                        binding.cardShopping.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardGift.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardJob.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardCell.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardHealth1.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardOuther2.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardCar.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                    }

                    R.id.card_outher_2 ->{
                        selectedCardView = 3
                        binding.cardOuther2.setBackgroundResource(R.drawable.background_rounded_card_objective)
                        binding.cardCompras.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardShopping.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardGift.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardJob.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardCell.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardHealth1.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardCar.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                    }
                }
            }
        }

    }


}