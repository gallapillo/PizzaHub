package com.gallapillo.pizzahub.model

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.gallapillo.pizzahub.DataLoadListener
import com.gallapillo.pizzahub.z_utils.NODE_PIZZA
import com.gallapillo.pizzahub.z_utils.REF_DATABASE_ROOT
import com.gallapillo.pizzaub.z_utils.showToast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class AppRepository {
    private lateinit var application: Application
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var userMutableLifeData: MutableLiveData<FirebaseUser>
    private lateinit var loggedOutMutableLiveData :MutableLiveData<Boolean>
    private var pizzas: ArrayList<Pizza> = ArrayList<Pizza>()
    private lateinit var appRepository: AppRepository


    private lateinit var mContext : Context
    private lateinit var mDataLoadListener: DataLoadListener

    fun getAppRepository(context: Context) : AppRepository {

        mContext = context
        if (appRepository == null) {
            appRepository = AppRepository(application)
        }
        mDataLoadListener = mContext as DataLoadListener
        return appRepository
    }

    fun getPizzas(): MutableLiveData<ArrayList<Pizza>> {
        loadNames()

        var pizza: MutableLiveData<ArrayList<Pizza>> = MutableLiveData()
        pizza.value = pizzas

        return pizza
    }

    private fun loadNames() {

        REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
        var query: Query = REF_DATABASE_ROOT.child(NODE_PIZZA)
        query.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                for (snapshot: DataSnapshot in p0.children) {
                    snapshot.getValue(Pizza::class.java)?.let { pizzas.add(it) }
                }
                mDataLoadListener.onNameLoaded()
            }

            override fun onCancelled(p0: DatabaseError) {

            }

        })
    }

    constructor(application: Application) {
        this.application = application

        firebaseAuth = FirebaseAuth.getInstance()
        userMutableLifeData = MutableLiveData<FirebaseUser>()
        loggedOutMutableLiveData = MutableLiveData<Boolean>()

        if (firebaseAuth.currentUser != null) {
            userMutableLifeData.postValue(firebaseAuth.currentUser)
            loggedOutMutableLiveData.postValue(false)
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    public fun register(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(application.mainExecutor, OnCompleteListener<AuthResult>() {
                if (it.isSuccessful) {
                    userMutableLifeData.postValue(firebaseAuth.currentUser)
                } else {
                    showToast(it.exception.toString())
                }
            })
    }

    @RequiresApi(Build.VERSION_CODES.P)
    public fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(application.mainExecutor, OnCompleteListener<AuthResult> {
                if (it.isSuccessful) {
                    userMutableLifeData.postValue(firebaseAuth.currentUser)
                } else {
                    showToast(it.exception.toString())
                }
            })
    }


    public fun logout() {
        firebaseAuth.signOut()
        loggedOutMutableLiveData.postValue(true)
    }

    public fun getUserMutableLiveData(): MutableLiveData<FirebaseUser> {
        return userMutableLifeData
    }

    public fun getLoggedOutMutableLiveData(): MutableLiveData<Boolean> {
        return loggedOutMutableLiveData
    }
}