package com.gallapillo.pizzahub.view

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProviders
import com.gallapillo.pizzahub.databinding.FragmentRegisterBinding
import com.gallapillo.pizzahub.viewmodel.LoginRegisterViewModel
import com.gallapillo.pizzaub.z_utils.replaceFragment
import com.gallapillo.pizzaub.z_utils.showToast


class RegisterFragment : Fragment() {

    private lateinit var mBinding: FragmentRegisterBinding

    private lateinit var loginRegisterViewModel: LoginRegisterViewModel

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentRegisterBinding.inflate(layoutInflater)

        mBinding.btnRegisterSubmit.setOnClickListener {
            val email = mBinding.fragmentRegisterLoginInput.text.toString()
            val password = mBinding.fragmentRegisterPasswordInput.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginRegisterViewModel.register(email, password)
            } else {
                showToast("Not Empty text")
            }
        }

        return mBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginRegisterViewModel = ViewModelProviders.of(this).get(
            LoginRegisterViewModel::class.java
        )
        loginRegisterViewModel.getUserMutableLiveData().observe(this,
            { user ->
                if (user != null) {
                    showToast("User created")
                    replaceFragment(MainListFragment.newInstance())
                }
            })
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegisterFragment()
    }
}