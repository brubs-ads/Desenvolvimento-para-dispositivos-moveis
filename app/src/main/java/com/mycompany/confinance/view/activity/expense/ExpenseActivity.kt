package com.mycompany.confinance.view.activity.expense

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mycompany.confinance.databinding.ActivityExpenseBinding
import com.mycompany.confinance.model.MovementModel
import com.mycompany.confinance.util.OnClickMovementListener
import com.mycompany.confinance.view.activity.MainActivity
import com.mycompany.confinance.view.adapter.MovementAdapter
import com.mycompany.confinance.viewmodel.MovementViewModel

class ExpenseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExpenseBinding
    private val viewModel: MovementViewModel by viewModels()
    private var listRevenue: ArrayList<MovementModel> = arrayListOf()
    private var id: Long? = null
    private val adapter = MovementAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getMovement("despesa")
        observe()
        handleClick()
    }

    private fun handleMovement() {
        val listener = object : OnClickMovementListener {
            override fun onClick(id: Long) {
                this@ExpenseActivity.id = id
//                viewModel.getMovementId(id= this@ExpenseActivity.id!!)
            }

            override fun delete(id: Long) {
                this@ExpenseActivity.id = id
                viewModel.deleteMovement(id = this@ExpenseActivity.id!!)
            }
        }
        adapter.setListener(listener)
    }

    private fun observe() {
        viewModel.isLoading.observe(this) {
            if (it == true) {
                recycler()
                adapter.startShimmerAnimation()
            } else if (it == false) {
                recycler()
                adapter.stopShimmerAnimation()
                viewModel.list.observe(this) { list ->
                    listRevenue = list as ArrayList
                    adapter.setList(listRevenue)
                }
            }
        }

        viewModel.isLoadingDelete.observe(this) {
            if (it) {
                updateMovement(id = this.id!!)
            } else {
                Toast.makeText(this, "erro ai paizão", Toast.LENGTH_SHORT).show()
            }
        }

//        viewModel.isLoadingGetMovement.observe(this){
//            if (it){
//                startActivity(Intent(this,CreateRevenueActivity::class.java))
//            }else{
//
//            }
//        }

    }

    private fun handleClick() {
        binding.arrowBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        binding.buttonCreateRevenue.setOnClickListener {
            startActivity(Intent(this, CreateExpenseActivity::class.java))
        }
    }

    private fun recycler() {
        binding.textGuia.visibility = View.GONE
        binding.textCreateRevenues.visibility = View.GONE
        binding.imageCreateRevenue.visibility = View.GONE
        binding.recycler.visibility = View.VISIBLE
        binding.recycler.layoutManager = LinearLayoutManager(applicationContext)
        binding.recycler.adapter = adapter
        handleMovement()
    }

    private fun updateMovement(id: Long) {
        var position = 0
        var movement: MovementModel? = null
        for (i in 0 until listRevenue.size) {
            if (listRevenue[i].id == id) {
                position = i
                movement = listRevenue[i]
            }
        }
        listRevenue.remove(movement)
        adapter.notifyItemRemoved(position)
    }
}