package com.example.observablepattern

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.observablepattern.observable.UserDataRepository
import com.example.observablepattern.observer.RepositoryObserver
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RepositoryObserver {
    lateinit var mUserDataRepository: UserDataRepository

    override fun onUserDataChanged(mUserName: String, mAge: Int) {
        textview.text = "Name: $mUserName, Age: $mAge"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mUserDataRepository = UserDataRepository().getInstance()
        mUserDataRepository.registerObserver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mUserDataRepository.unregisterObserver(this)
    }
}
