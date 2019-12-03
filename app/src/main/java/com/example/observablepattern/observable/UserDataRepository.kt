package com.example.observablepattern.observable

import android.os.Handler
import com.example.observablepattern.observer.RepositoryObserver
import java.util.*
import kotlin.collections.ArrayList

class UserDataRepository() : Observable {
    lateinit var mUserName: String
    var mAge: Int = 0
    private var mInstance: UserDataRepository? = null
    private var mObservers: ArrayList<RepositoryObserver>? = null
    private val sampleAlphabets: String = "ABCDEFGHIJKLMNOPQRSTUVXYZ"
    private val sampleSize: Int = 50

    init {
        mObservers = ArrayList()
        generateRandomData()
    }

    fun getInstance(): UserDataRepository {
        // Create a singleton of the class
        if (mInstance == null)
            mInstance = UserDataRepository()
        return mInstance as UserDataRepository
    }

    override fun registerObserver(repositoryObserver: RepositoryObserver) {
        if (repositoryObserver !in mObservers!!)
            mObservers!!.add(repositoryObserver)
    }

    override fun unregisterObserver(repositoryObserver: RepositoryObserver) {
        if (repositoryObserver in mObservers!!)
            mObservers!!.remove(repositoryObserver)
    }

    override fun notifyObservers() {
        for (mObserver in mObservers!!)
            mObserver.onUserDataChanged(mUserName, mAge)
    }

    fun setUserData(mUserName: String, mAge: Int) {
        this.mUserName = mUserName
        this.mAge = mAge
        notifyObservers()
    }

    fun generateRandomData() {
        Handler().postDelayed({
            mUserName = sampleAlphabets.get(Random().nextInt(sampleAlphabets.length - 1)).toString()
            mAge = Random().nextInt(sampleSize)
            setUserData(mUserName, mAge)
        }, 2000)
    }
}