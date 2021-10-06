package com.gallapillo.pizzahub.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gallapillo.pizzahub.R
import com.gallapillo.pizzaub.z_utils.APP_ACTIVITY
import com.gallapillo.pizzaub.z_utils.replaceFragment


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        APP_ACTIVITY = this
        replaceFragment(ChoseRegistrationFragment.newInstance())
    }
}