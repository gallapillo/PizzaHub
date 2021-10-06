package com.gallapillo.pizzahub.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gallapillo.pizzahub.databinding.FragmentChoseRegistraionBinding
import com.gallapillo.pizzaub.z_utils.replaceFragment


class ChoseRegistrationFragment : Fragment() {

    private lateinit var mBinding: FragmentChoseRegistraionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentChoseRegistraionBinding.inflate(layoutInflater)
        toggleButtons()

        return mBinding.root
    }


    private fun toggleButtons() {
        mBinding.btnChooseEmailAuth.setOnClickListener {
            replaceFragment(LoginFragment.newInstance())
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ChoseRegistrationFragment()
    }
}