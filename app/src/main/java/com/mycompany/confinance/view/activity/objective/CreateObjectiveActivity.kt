package com.mycompany.confinance.view.activity.objective

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.R.color
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityCreateObjectiveBinding
import com.mycompany.confinance.util.DatePickerFragment
import com.mycompany.confinance.viewmodel.objective.CreateObjectiveViewModel

class CreateObjectiveActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateObjectiveBinding
    private val viewModel: CreateObjectiveViewModel by viewModels()
    private var selectedCardView: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateObjectiveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleClick()
        handleCategory()
        observe()
    }

    private fun observe() {
        viewModel.isLoading.observe(this){
            if (it){
                startActivity(Intent(this,ObjectiveActivity::class.java))
                finish()
            }else{
                Toast.makeText(this,"erro", Toast.LENGTH_SHORT).show()
            }
        }
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
        val photo = selectedCardView

        viewModel.createObjective(
            value = value,
            savedValue = squared,
            description = descripton,
            date = date,
            photo = photo
        )

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
            binding.cardHouse,
            binding.cardMarketplace,
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
                        binding.cardMarketplace.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardHouse.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                    }

                    R.id.card_house -> {
                        selectedCardView = 2
                        binding.cardHouse.setBackgroundResource(R.drawable.background_rounded_card_objective)
                        binding.cardGift.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardJob.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardCell.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardHealth1.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardOuther2.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardCar.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardMarketplace.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                    }
                    R.id.card_cell -> {
                        selectedCardView = 3
                        binding.cardCell.setBackgroundResource(R.drawable.background_rounded_card_objective)
                        binding.cardHealth1.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardOuther2.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardCar.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardGift.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardJob.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardMarketplace.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardHouse.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                    }
                    R.id.card_Marketplace -> {
                        selectedCardView = 4
                        binding.cardMarketplace.setBackgroundResource(R.drawable.background_rounded_card_objective)
                        binding.cardHouse.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardGift.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardJob.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardCell.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardHealth1.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardOuther2.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardCar.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                    }
                    R.id.card_gift -> {
                        selectedCardView = 5
                        binding.cardGift.setBackgroundResource(R.drawable.background_rounded_card_objective)
                        binding.cardJob.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardCell.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardHealth1.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardOuther2.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardCar.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardMarketplace.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardHouse.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                    }
                    R.id.card_car -> {
                        selectedCardView = 6
                        binding.cardCar.setBackgroundResource(R.drawable.background_rounded_card_objective)
                        binding.cardCell.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardHealth1.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardOuther2.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardGift.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardJob.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardMarketplace.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardHouse.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                    }


                    R.id.card_job -> {
                        selectedCardView = 7
                        binding.cardJob.setBackgroundResource(R.drawable.background_rounded_card_objective)
                        binding.cardCell.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardHealth1.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardOuther2.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardCar.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardGift.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardMarketplace.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardHouse.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                    }




                    R.id.card_outher_2 -> {
                        selectedCardView = 8
                        binding.cardOuther2.setBackgroundResource(R.drawable.background_rounded_card_objective)
                        binding.cardMarketplace.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
                        binding.cardHouse.setBackgroundResource(color.mtrl_btn_transparent_bg_color)
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