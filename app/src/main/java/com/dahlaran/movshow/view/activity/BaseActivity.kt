package com.dahlaran.movshow.view.activity

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(){
    fun showNavigationSupportBar() {
        supportActionBar?.show()
    }

    fun hideNavigationSupportBar() {
        supportActionBar?.hide()
    }
}