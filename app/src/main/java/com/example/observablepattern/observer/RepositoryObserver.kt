package com.example.observablepattern.observer

interface RepositoryObserver {
    fun onUserDataChanged(mUserName: String, mAge: Int)
}