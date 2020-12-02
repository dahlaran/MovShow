package com.dahlaran.movshow.view.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.dahlaran.movshow.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        showNavigationSupportBar()

        val navigationController =
            supportFragmentManager.findFragmentById(R.id.navigationHostFragment) as NavHostFragment

        setupActionBarWithNavController(navigationController.navController)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            showNavigationSupportBar()
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    fun showNavigationSupportBar() {
        supportActionBar?.show()
    }

    fun hideNavigationSupportBar() {
        supportActionBar?.hide()
    }
}