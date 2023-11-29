package com.mycompany.confinance.view.activity

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityGraphicBinding
import com.mycompany.confinance.viewmodel.GraphicViewModel
import ir.mahozad.android.PieChart.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class GraphicActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGraphicBinding
    private val viewModel: GraphicViewModel by viewModels()
    private val calendar = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGraphicBinding.inflate(layoutInflater)
        setContentView(binding.root)

         binding.textTotal.paintFlags = (Paint.UNDERLINE_TEXT_FLAG)



        checkMonthAndYear()
        updateMonthYearText()
        observe()

    }

    private fun observe() {
        viewModel.totalMovement.observe(this){
            binding.textCardRevenue.text = formatarNumero(it.totalRevenues)
            binding.textCardExpense.text = formatarNumero(it.totalExpenses)
            binding.textRevenue.text = "Receitas ${String.format("%.2f%%", it.percentageRevenue.toDouble() * 100)}"
            binding.textExpense.text ="Despesa ${String.format("%.2f%%", it.percentageExpense.toDouble() * 100)}"

            binding.pieChart.apply {
                slices = listOf(
                    Slice(it.percentageRevenue.toFloat(), Color.rgb(104,179,162), Color.rgb(255, 87, 51)),
                    Slice(it.percentageExpense.toFloat(), Color.rgb(255, 87, 51), Color.rgb(104,179,162))
                )
                gradientType = GradientType.RADIAL
                overlayRatio = 0f
            }
        }
    }

    private fun checkMonthAndYear() {
        binding.arrowBack.setOnClickListener {
            if (calendar.get(Calendar.MONTH) == Calendar.JANUARY) {
                calendar.add(Calendar.YEAR, -1)
                calendar.set(Calendar.MONTH, Calendar.DECEMBER)
            } else {
                calendar.add(Calendar.MONTH, -1)
            }
            updateMonthYearText()
            handleQuery()
        }

        binding.arrowNext.setOnClickListener {
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


        binding.textMonth.text = formattedDate
        handleQuery()
    }

    private fun handleQuery() {
        val displayedDate = binding.textMonth.text.toString()
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
                viewModel.queryMonthAndYear(month = month,year = year)
            }
        }
    }

    private fun formatarNumero(numero: Double): String {
        val formato = DecimalFormat("#,##0.00", DecimalFormatSymbols(Locale("pt", "BR")))
        formato.isGroupingUsed = true
        formato.groupingSize = 3

        return "R$ " + formato.format(numero)
    }

}