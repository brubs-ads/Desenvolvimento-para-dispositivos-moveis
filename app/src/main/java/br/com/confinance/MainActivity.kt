package br.com.confinance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.confinance.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        setOnClick()

    }
    override fun onClick(view: View) {

        when(view.id){
            R.id.button_create_account ->{
                Toast.makeText(this,"TESTEEEE", Toast.LENGTH_SHORT).show()
            }
            R.id.text_login ->{
                Toast.makeText(this,"ESSE AQUI", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setOnClick() {
        binding.buttonCreateAccount.setOnClickListener(this)
        binding.textLogin.setOnClickListener(this)
    }


}