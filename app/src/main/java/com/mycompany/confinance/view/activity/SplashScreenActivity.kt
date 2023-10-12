package com.mycompany.confinance.view.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.confinance.R
import com.mycompany.confinance.databinding.ActivitySplashScreenBinding
import com.mycompany.confinance.databinding.CustomDialogNoConnectionSplashBinding
import com.mycompany.confinance.view.activity.user.CreateAccountActivity
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
    private lateinit var dialog: AlertDialog
    private var acess: Boolean? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        acess = sharedPreferences.getBoolean("http_connected", false)

        checkInternetConnectivity()

    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun checkInternetConnectivity() {
        GlobalScope.launch(Dispatchers.IO) {
            val isConnected = isInternetConnected()
            runOnUiThread {
                if (isConnected) {
                    if (acess == true){
                        startActivity(
                            Intent(
                                this@SplashScreenActivity,
                                MainActivity::class.java
                            )
                        )
                        finish()
                    }else{
                        startActivity(
                            Intent(
                                this@SplashScreenActivity,
                                CreateAccountActivity::class.java
                            )
                        )
                        finish()
                    }
                } else {
                    val build = AlertDialog.Builder(this@SplashScreenActivity, R.style.ThemeCustomDialog)
                    val dialogBinding =
                        CustomDialogNoConnectionSplashBinding.inflate(
                            LayoutInflater.from(this@SplashScreenActivity)
                        )
                    dialogBinding.buttonOk.setOnClickListener {
                        dialog.dismiss()
                        Handler(Looper.getMainLooper()).postDelayed({
                            checkInternetConnectivity()
                        }, 3000)
                    }

                    build.setView(dialogBinding.root)
                    dialog = build.create()
                    dialog.show()

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