package com.gallapillo.pizzahub.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gallapillo.pizzahub.model.AppRepository
import com.gallapillo.pizzahub.model.Pizza
import com.gallapillo.pizzaub.z_utils.showToast

class PizzaViewModel : AndroidViewModel {
    private lateinit var appRepository: AppRepository
    private var pizzas : MutableLiveData<ArrayList<Pizza>> = MutableLiveData<ArrayList<Pizza>>()

    public constructor(application: Application) : super(application) {
        appRepository = AppRepository(application)
        pizzas = appRepository.getPizzas()
    }

    public fun init(context: Context){
        if (pizzas != null) {
            return
        } else {
            showToast("NOT PIZAS")
        }

        pizzas = appRepository.getAppRepository(context).getPizzas()
    }

    public fun getPizzas(): LiveData<ArrayList<Pizza>> {
        return pizzas
    }

}