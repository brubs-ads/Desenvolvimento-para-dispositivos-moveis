package com.mycompany.confinance.view.objective

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mycompany.confinance.controller.ObjectiveController
import com.mycompany.confinance.databinding.ActivityObjectiveBinding
import com.mycompany.confinance.model.objective.ObjectiveModel
import com.mycompany.confinance.view.OnMovementListener
import com.mycompany.confinance.view.adapter.ObjectiveAdapter
import com.mycompany.confinance.view.main.InitialActivity


class ObjectiveActivity : AppCompatActivity() {
    private lateinit var binding: ActivityObjectiveBinding
    private val controller = ObjectiveController()
    private val adapter = ObjectiveAdapter()
    private var listObjective: ArrayList<ObjectiveModel> = arrayListOf()
    private var isEditing = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityObjectiveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleClick()
        getObjective()


    }

    private fun handleClick() {
        binding.imageArrowBackObjective.setOnClickListener {
            startActivity(Intent(this, InitialActivity::class.java))
            finish()
        }
        binding.imageAddObjective.setOnClickListener {
            startActivity(Intent(this, CreateObjectiveActivity::class.java))
            finish()
        }
    }

    private fun getObjective() {
        controller.getObjective(onSuccess = {
            listObjective =  it as ArrayList
            recyclerView()
        }, onFailure = {
            Toast.makeText(this,it,Toast.LENGTH_LONG).show()
        })
    }

    private fun update(id: Long) {
        var position = 0
        var movement: ObjectiveModel? = null
        for (i in 0 until listObjective.size){
            if (listObjective[i].id == id) {
                position = i
                movement = listObjective[i]
            }
        }
        listObjective.remove(movement)
        adapter.notifyItemRemoved(position)
    }

    private fun handleObjective() {
        val listener = object : OnMovementListener {
            override fun onClick(id: Long) {
                val intent = Intent(this@ObjectiveActivity, CreateObjectiveActivity::class.java)
                val selectedObjective = listObjective.firstOrNull { it.id == id }
                selectedObjective?.let {
                    isEditing = true // Indica que está editando o objetivo
                    intent.putExtra("objective", it)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onDelete(id: Long) {
                controller.deleteObjectiveById(id, result = { message, status ->
                    if (status) {
                        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
                        update(id)
                    } else {
                        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
        adapter.clickObjective(listener)
    }



    private fun recyclerView() {
        binding.textView7.visibility = View.GONE
        binding.imageView.visibility = View.GONE
        binding.textCreateObjective.visibility = View.GONE
        binding.textInfo.visibility = View.GONE

        binding.recycler.layoutManager = LinearLayoutManager(applicationContext)
        binding.recycler.adapter = adapter

        adapter.updateObjective(listObjective)
        handleObjective()

    }
}