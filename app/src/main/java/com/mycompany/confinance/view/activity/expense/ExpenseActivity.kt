package com.mycompany.confinance.view.activity.expense

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivityExpenseBinding
import com.mycompany.confinance.databinding.CustomDialogDeleteExpenseBinding
import com.mycompany.confinance.databinding.CustomDialogEditExpenseBinding
import com.mycompany.confinance.model.MovementModel
import com.mycompany.confinance.util.OnClickMovementListener
import com.mycompany.confinance.view.activity.MainActivity
import com.mycompany.confinance.view.adapter.MovementAdapter
import com.mycompany.confinance.viewmodel.MovementViewModel

class ExpenseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExpenseBinding
    private val viewModel: MovementViewModel by viewModels()
    private var listRevenue: ArrayList<MovementModel> = arrayListOf()
    private val adapter = MovementAdapter()
    private var id: Long? = null
    private var dialogDelete: AlertDialog? = null
    private var dialogEdit: AlertDialog? = null
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
                dialogEdit()
            }

            override fun delete(id: Long) {
                this@ExpenseActivity.id = id
                dialogDelete(this@ExpenseActivity.id!!)
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

    private fun dialogEdit() {
        if (dialogEdit != null && dialogEdit?.isShowing == true) {
            dialogEdit?.dismiss()
        }

        val build = AlertDialog.Builder(applicationContext, R.style.ThemeCustomDialog)
        val dialogBinding =
            CustomDialogEditExpenseBinding.inflate(LayoutInflater.from(applicationContext))
        dialogBinding.buttonYesEdit.setOnClickListener {
            dialogEdit?.dismiss()
        }
        dialogBinding.buttonCancell.setOnClickListener {
            dialogEdit?.dismiss()
        }
        dialogEdit = build.setView(dialogBinding.root).create()
        dialogEdit?.show()
    }

    private fun dialogDelete(id: Long){
        if (dialogDelete != null && dialogDelete?.isShowing == true) {
            dialogDelete?.dismiss()
        }

        val build = AlertDialog.Builder(applicationContext, R.style.ThemeCustomDialog)
        val dialogBinding =
            CustomDialogDeleteExpenseBinding.inflate(LayoutInflater.from(applicationContext))

        dialogBinding.buttonYesDelete.setOnClickListener {
            dialogDelete?.dismiss()
            viewModel.deleteMovement(id)
        }
        dialogBinding.buttonCancell.setOnClickListener {
            dialogDelete?.dismiss()
        }

        dialogDelete = build.setView(dialogBinding.root).create()
        dialogDelete?.show()
    }
}
