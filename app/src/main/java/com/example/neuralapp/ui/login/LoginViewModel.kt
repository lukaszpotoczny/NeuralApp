package com.example.neuralapp.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.neuralapp.base.BaseViewModel
import com.example.neuralapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    val loginStateLiveData = MutableLiveData<Boolean>()

    fun login(username: String, password: String) {
        disposableObservables.add(
            userRepository.findUser(username).subscribe(
                { user ->
                    user?.let {
                        loginStateLiveData.value = it.password == password
                    } ?: run {
                        loginStateLiveData.value = false
                    }

                }, {
                    loginStateLiveData.value = false
                }
            ))
    }

}