package com.gallapillo.pizzahub.viewmodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.gallapillo.pizzahub.model.AppRepository
import com.google.firebase.auth.FirebaseUser

class LoginRegisterViewModel : AndroidViewModel {
    private lateinit var appRepository: AppRepository
    private lateinit var userMutableLiveData: MutableLiveData<FirebaseUser>

    constructor(application: Application) : super(application) {
        appRepository = AppRepository(application)
        userMutableLiveData = appRepository.getUserMutableLiveData()
    }


    @RequiresApi(Build.VERSION_CODES.P)
    public fun register(email: String, password: String) {
        appRepository.register(email, password);
    }

    @RequiresApi(Build.VERSION_CODES.P)
    public fun login(email: String, password: String) {
        appRepository.login(email, password)
    }

    public fun getUserMutableLiveData(): MutableLiveData<FirebaseUser> {
        return userMutableLiveData
    }

}