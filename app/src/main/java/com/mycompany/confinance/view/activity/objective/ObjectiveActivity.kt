package com.mycompany.confinance.view.activity.objective

import android.content.Intent
import android.content.LocusId
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityObjectiveBinding
import com.mycompany.confinance.databinding.CustomDialogDeleteExpenseBinding
import com.mycompany.confinance.databinding.CustomDialogDeleteObjectiveBinding
import com.mycompany.confinance.databinding.CustomDialogEditObjectiveBinding
import com.mycompany.confinance.model.MovementModel
import com.mycompany.confinance.model.ObjectiveModel
import com.mycompany.confinance.util.OnClickMovementListener
import com.mycompany.confinance.view.adapter.ObjectiveAdapter
import com.mycompany.confinance.viewmodel.objective.ObjectiveViewModel

class ObjectiveActivity : AppCompatActivity() {
    private lateinit var binding: ActivityObjectiveBinding
    private val viewModel: ObjectiveViewModel by viewModels()
    private val adapter = ObjectiveAdapter()
    private var listObjective: ArrayList<ObjectiveModel> = arrayListOf()
    private var id: Long? = null
    private var dialogEdit: AlertDialog? = null
    private var dialogDelete: AlertDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityObjectiveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getObjective()
        handleClick()
        observe()

    }

    private fun handleClick() {
        binding.imgBack.setOnClickListener {
            finish()
        }

        binding.buttonCreateObjective.setOnClickListener {
            startActivity(Intent(this, CreateObjectiveActivity::class.java))
            finish()
        }
    }

    private fun observe() {
        viewModel.isLoading.observe(this) { loading ->
            if (loading == true) {
                recycler()
                adapter.startShimmerAnimation()
            } else if (loading == false) {
                adapter.stopShimmerAnimation()
                viewModel.list.observe(this) { list ->
                    listObjective = list as ArrayList
                    adapter.setList(listObjective)
                }
            } else {
                listObjective.clear()
                binding.recyclerObjective.visibility = View.GONE
                binding.imgObjective.visibility = View.VISIBLE
                binding.textCreateObjective.visibility = View.VISIBLE
                binding.textInformationObjective.visibility = View.VISIBLE
                binding.text.visibility = View.VISIBLE
            }
        }

        viewModel.isLoadingDelete.observe(this){
            if (it){
                updateMovement(id!!)
            }else{
                Toast.makeText(this, "ERRO", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun recycler() {
        binding.textCreateObjective.visibility = View.GONE
        binding.textInformationObjective.visibility = View.GONE
        binding.imgObjective.visibility = View.GONE
        binding.text.visibility = View.GONE
        binding.recyclerObjective.visibility = View.VISIBLE
        binding.recyclerObjective.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerObjective.adapter = adapter
        handleMovement()
    }

    private fun handleMovement() {
        val listener = object : OnClickMovementListener {
            override fun onClick(id: Long) {
                dialogEdit()
            }

            override fun delete(id: Long) {
                this@ObjectiveActivity.id = id
                dialogDelete(this@ObjectiveActivity.id!!)
            }
        }
        adapter.setListener(listener)
    }

    private fun dialogEdit() {
        if (dialogEdit != null && dialogEdit?.isShowing == true) {
            dialogEdit?.dismiss()
        }

        val build = AlertDialog.Builder(this, R.style.ThemeCustomDialog)
        val dialogBinding =
            CustomDialogEditObjectiveBinding.inflate(LayoutInflater.from(this))
        dialogBinding.buttonYes.setOnClickListener {
            dialogEdit?.dismiss()
            startActivity(Intent(this, CreateObjectiveActivity::class.java))
        }
        dialogBinding.buttonCancell.setOnClickListener {
            dialogEdit?.dismiss()
        }
        dialogEdit = build.setView(dialogBinding.root).create()
        dialogEdit?.show()
    }

    private fun dialogDelete(id: Long) {
        if (dialogDelete != null && dialogDelete?.isShowing == true) {
            dialogDelete?.dismiss()
        }

        val build = AlertDialog.Builder(this, R.style.ThemeCustomDialog)
        val dialogBinding =
            CustomDialogDeleteObjectiveBinding.inflate(LayoutInflater.from(this))

        dialogBinding.buttonYesDelete.setOnClickListener {
            dialogDelete?.dismiss()
            viewModel.deleteObjective(id)
        }
        dialogBinding.buttonCancell.setOnClickListener {
            dialogDelete?.dismiss()
        }

        dialogDelete = build.setView(dialogBinding.root).create()
        dialogDelete?.show()
    }

    private fun updateMovement(id: Long) {
        var position = 0
        var movement: ObjectiveModel? = null
        for (i in 0 until listObjective.size) {
            if (listObjective[i].id == id) {
                position = i
                movement = this.listObjective[i]
            }
        }
        listObjective.remove(movement)
        adapter.notifyItemRemoved(position)
    }

}