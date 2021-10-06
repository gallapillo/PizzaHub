package com.gallapillo.pizzahub.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.gallapillo.pizzahub.model.AppRepository
import com.google.firebase.auth.FirebaseUser

class LoggedInViewModel: AndroidViewModel {

    private lateinit var appRepository: AppRepository
    private lateinit var userMutableLiveData: MutableLiveData<FirebaseUser>
    private lateinit var loggedInMutableLiveData: MutableLiveData<Boolean>

    constructor(application: Application) : super(application) {
        appRepository = AppRepository(application)
        userMutableLiveData = appRepository.getUserMutableLiveData()
        loggedInMutableLiveData = appRepository.getLoggedOutMutableLiveData()
    }

    public fun logOut() {
        appRepository.logout()
    }

    public fun getUserMutableLiveData(): MutableLiveData<FirebaseUser> {
        return userMutableLiveData
    }

    public fun getLoggedOutMutableLiveData(): MutableLiveData<Boolean> {
        return loggedInMutableLiveData
    }

}