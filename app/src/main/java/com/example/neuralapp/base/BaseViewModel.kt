package com.example.neuralapp.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    var disposableObservables = CompositeDisposable()

    public override fun onCleared() {
        super.onCleared()
        disposableObservables.dispose()
    }
}