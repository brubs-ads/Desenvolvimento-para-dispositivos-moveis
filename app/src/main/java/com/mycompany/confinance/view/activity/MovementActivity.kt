package com.mycompany.confinance.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mycompany.confinance.databinding.ActivityMovementBinding
import com.mycompany.confinance.model.MovementModel
import com.mycompany.confinance.view.adapter.MovementAdapter
import com.mycompany.confinance.viewmodel.MovementViewModel

class MovementActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovementBinding
    private val viewModel: MovementViewModel by viewModels()
    private val adapter = MovementAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getMovement("receita")
        observe()
        handleClick()

    }

    private fun observe() {
        viewModel.list.observe(this){
            recycler(it)
        }
    }

    private fun handleClick() {
        binding.arrowBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }


    private fun recycler(listRevenue:List<MovementModel>) {
        binding.imageCreateRevenue.visibility = View.GONE
        binding.textCreateRevenues.visibility = View.GONE
        binding.textGuia.visibility = View.GONE

        binding.recycler.layoutManager = LinearLayoutManager(applicationContext)
        binding.recycler.adapter = adapter
        adapter.setList(listRevenue)
    }


}