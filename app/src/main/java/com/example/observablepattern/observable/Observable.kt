package com.example.observablepattern.observable

import com.example.observablepattern.observer.RepositoryObserver

interface Observable {
    fun registerObserver(repositoryObserver: RepositoryObserver)
    fun unregisterObserver(repositoryObserver: RepositoryObserver)
    fun notifyObservers()
}