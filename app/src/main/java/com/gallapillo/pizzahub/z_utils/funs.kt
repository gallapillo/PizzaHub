package com.gallapillo.pizzaub.z_utils

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.gallapillo.pizzahub.R
import com.gallapillo.pizzahub.view.MainActivity

lateinit var APP_ACTIVITY: MainActivity

fun replaceFragment(fragment: Fragment, addStack: Boolean = true) {
    if (addStack) {
        APP_ACTIVITY.supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(
                R.id.main_blank,
                fragment
            ).commit()
    } else {
        APP_ACTIVITY.supportFragmentManager.beginTransaction()
            .replace(
                R.id.main_blank,
                fragment
            ).commit()
    }

}

fun showToast(message: String) {
    Toast.makeText(APP_ACTIVITY, message, Toast.LENGTH_SHORT).show()
}