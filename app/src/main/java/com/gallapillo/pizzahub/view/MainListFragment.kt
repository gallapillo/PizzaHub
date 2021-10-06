package com.gallapillo.pizzahub.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.gallapillo.pizzahub.DataLoadListener
import com.gallapillo.pizzahub.databinding.FragmentMainListBinding
import com.gallapillo.pizzahub.model.Pizza
import com.gallapillo.pizzahub.viewmodel.LoggedInViewModel
import com.gallapillo.pizzahub.viewmodel.PizzaViewModel
import com.gallapillo.pizzaub.z_utils.APP_ACTIVITY
import com.gallapillo.pizzaub.z_utils.replaceFragment
import com.gallapillo.pizzaub.z_utils.showToast
import com.google.firebase.auth.FirebaseUser


class MainListFragment : Fragment(), DataLoadListener {

    private lateinit var mBinding: FragmentMainListBinding
    private lateinit var loggedInViewModel: LoggedInViewModel
    private lateinit var mAdapter: MainListAdapter
    private lateinit var mPizzaViewModel: PizzaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMainListBinding.inflate(layoutInflater)

        return mBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = FragmentMainListBinding.inflate(layoutInflater)
        loggedInViewModel = ViewModelProviders.of(this).get(LoggedInViewModel::class.java)

        loggedInViewModel.getUserMutableLiveData().observe(this, Observer<FirebaseUser>() {
            if (it != null) {
                showToast("Logged User")
            }
        })

        loggedInViewModel.getLoggedOutMutableLiveData().observe(this, Observer<Boolean>(){
            if (it) {
                replaceFragment(ChoseRegistrationFragment.newInstance())
            }
        })

        mBinding.pizzaList.setHasFixedSize(true)
        mBinding.pizzaList.layoutManager = LinearLayoutManager(this.context)
        mPizzaViewModel = ViewModelProviders.of(this).get(PizzaViewModel::class.java)
        mPizzaViewModel.init(APP_ACTIVITY)

        mAdapter = MainListAdapter(mPizzaViewModel.getPizzas().value)

        mBinding.pizzaList.adapter = mAdapter
    }

    fun initRecyclerView() {
        mBinding.pizzaList.setHasFixedSize(true)
        mBinding.pizzaList.layoutManager = LinearLayoutManager(this.context)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainListFragment()
    }

    override fun onNameLoaded() {
        mPizzaViewModel.getPizzas().observe(this, object : Observer<ArrayList<Pizza>> {
            override fun onChanged(t: ArrayList<Pizza>?) {
                mAdapter.notifyDataSetChanged()
            }

        })
    }
}