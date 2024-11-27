package com.ayoolamasha.fakestore.entryPoints

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ayoolamasha.fakestore.R
import com.ayoolamasha.fakestore.featureProducts.presentation.ProductsActivity

class MainActivity : AppCompatActivity() {
    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        splashScreen.apply {
            setKeepOnScreenCondition {
                mainActivityViewModel.isLoadingSplashScreen.value
            }

            appLaunchCheck()
        }

        appLaunchCheck()
    }

    private fun appLaunchCheck() {

        try {
            val intent = Intent(this@MainActivity, ProductsActivity::class.java)
            startActivity(intent)
            finish()
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }
}