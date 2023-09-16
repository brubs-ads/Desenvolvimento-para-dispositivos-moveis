package com.mycompany.confinance.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket


@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkInternetConnectivity()

    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun checkInternetConnectivity() {
        GlobalScope.launch(Dispatchers.IO) {
            val isConnected = isInternetConnected()
            runOnUiThread {
                if (isConnected) {
                    startActivity(
                        Intent(
                            this@SplashScreenActivity,
                            CreateAccountActivity::class.java
                        )
                    )
                    finish()
                } else {
                    binding.progressBar.visibility = View.GONE
                    binding.cardInformation.visibility = View.VISIBLE
                    binding.buttonCardInformation.setOnClickListener {
                        binding.cardInformation.visibility = View.GONE
                        checkInternetConnectivity()
                    }
                }
            }
        }
    }

    private fun isInternetConnected(): Boolean {
        return try {
            val socket = Socket()
            socket.connect(InetSocketAddress("8.8.8.8", 53), 3000)
            socket.close()
            true
        } catch (e: IOException) {
            false
        }
    }


}