package com.mycompany.confinance.view.activity

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityGraphicBinding
import com.mycompany.confinance.viewmodel.GraphicViewModel
import ir.mahozad.android.PieChart.GradientType
import ir.mahozad.android.PieChart.Slice
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

        checkMonthAndYear()
        updateMonthYearText()
        observe()
        handleClick()

    }

    private fun handleClick() {
        binding.arrow.setOnClickListener {
            finish()
        }
    }

    private fun observe() {
        viewModel.isLoading.observe(this) { loading ->
            if (loading) {
                viewModel.totalMovement.observe(this) { total ->
                    if (total.totalExpenses == 0.0 && total.totalRevenues == 0.0) {
                        binding.progressBar.visibility = View.GONE
                        binding.imageGraphics.visibility = View.VISIBLE
                        binding.textGraphics.visibility = View.VISIBLE
                        binding.pieChart.visibility = View.GONE
                        binding.view1.visibility = View.GONE
                        binding.textRevenue.visibility = View.GONE
                        binding.view2.visibility = View.GONE
                        binding.textExpense.visibility = View.GONE
                        binding.textTotal.visibility = View.GONE
                        binding.viewCardRevenue.visibility = View.GONE
                        binding.viewCardExpense.visibility = View.GONE
                    } else {
                        binding.imageGraphics.visibility = View.GONE
                        binding.textGraphics.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        binding.pieChart.visibility = View.VISIBLE
                        binding.view1.visibility = View.VISIBLE
                        binding.textRevenue.visibility = View.VISIBLE
                        binding.view2.visibility = View.VISIBLE
                        binding.textExpense.visibility = View.VISIBLE
                        binding.textTotal.visibility = View.VISIBLE
                        binding.textTotal.paintFlags = (Paint.UNDERLINE_TEXT_FLAG)
                        binding.viewCardRevenue.visibility = View.VISIBLE
                        binding.viewCardExpense.visibility = View.VISIBLE

                        binding.textCardRevenue.text = formatarNumero(total.totalRevenues)
                        binding.textCardExpense.text = formatarNumero(total.totalExpenses)
                        binding.textRevenue.text =
                            "Receitas ${String.format("%.2f%%", total.percentageRevenue.toDouble() * 100)}"
                        binding.textExpense.text =
                            "Despesa ${String.format("%.2f%%", total.percentageExpense.toDouble() * 100)}"

                        binding.pieChart.apply {
                            slices = listOf(
                                Slice(
                                    total.percentageRevenue.toFloat(),
                                    Color.rgb(104, 179, 162),
                                    Color.rgb(104, 179, 162)
                                ),
                                Slice(
                                    total.percentageExpense.toFloat(),
                                    Color.rgb(255, 87, 51),
                                    Color.rgb(255, 87, 51)
                                )
                            )
                            gradientType = GradientType.RADIAL
                            overlayRatio = 0f
                        }
                    }
                }
            } else {
                viewModel.erro.observe(this) {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
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
            val currentMonthAbbreviation = monthAbbreviationsArray[Calendar.getInstance().get(Calendar.MONTH)]
            val monthIndex = monthAbbreviationsArray.indexOfFirst {
                it.equals(displayedDate.substring(0, 3), ignoreCase = true)
            }

            if (monthIndex >= 0) {
                val yearString = if (displayedDate.length == 7) {
                    val selectedYear = displayedDate.substring(4)
                    val formattedYear = if (selectedYear.length == 2) {
                        "20$selectedYear"
                    } else {
                        selectedYear
                    }
                    formattedYear
                } else {
                    if (displayedDate.substring(0, 3).equals(currentMonthAbbreviation, ignoreCase = true)) {
                        currentYear.toString()
                    } else {
                        displayedDate.substring(4)
                    }
                }

                val year = try {
                    if (yearString.length == 2){
                        val yw ="20${yearString}"
                        yw.toInt()

                    } else {
                        yearString.toInt()
                    }
                } catch (e: NumberFormatException) {
                    currentYear
                }

                val month = monthIndex + 1
                viewModel.queryMonthAndYear(month, year)
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