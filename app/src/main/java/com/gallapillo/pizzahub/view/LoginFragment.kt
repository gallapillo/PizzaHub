package com.gallapillo.pizzahub.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.gallapillo.pizzahub.databinding.FragmentLoginBinding
import com.gallapillo.pizzahub.viewmodel.LoginRegisterViewModel
import com.gallapillo.pizzaub.z_utils.replaceFragment
import com.gallapillo.pizzaub.z_utils.showToast


class LoginFragment : Fragment() {

    private lateinit var mBinding: FragmentLoginBinding

    private lateinit var loginRegisterViewModel: LoginRegisterViewModel

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentLoginBinding.inflate(layoutInflater)
        mBinding.btnLoginSubmit.setOnClickListener {
            val email = mBinding.fragmentLoginLoginInput.text.toString()
            val password = mBinding.fragmentLoginPasswordInput.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginRegisterViewModel.login(email, password)
            } else {
                showToast("is empty")
            }
        }
        mBinding.btnRegister.setOnClickListener {
            replaceFragment(RegisterFragment.newInstance())
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
                    showToast("Welcome")
                    replaceFragment(MainListFragment.newInstance())
                }
            })
    }


    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}