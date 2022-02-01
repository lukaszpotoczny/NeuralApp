package com.example.neuralapp.ui.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.neuralapp.base.BaseViewModel
import com.example.neuralapp.data.db.User
import com.example.neuralapp.data.db.UserDao
import com.example.neuralapp.data.model.House
import com.example.neuralapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val users = mutableListOf<User>()

    val registerStateLiveData = MutableLiveData<Boolean>()



    fun onRegisterClick(username: String, password: String) {
        disposableObservables.add(
            userRepository.getUsers().subscribe(
                { userResponse ->
                    userResponse?.let {
                        users.clear()
                        users.addAll(it)
                    }
                    register(username, password)
                }, {
                    Log.e("dupa", "error1")

                }
            ))

    }

    private fun register(username: String, password: String){
        if (users.any { it.username == username}) {
            Log.e("dupa", "cant register")
        } else {
            val user = User(username, password)
            disposableObservables.add(
                userRepository.addUser(user).subscribe(
                    { userResponse ->
                        Log.e("dupa", "done")
                        registerStateLiveData.value = true

                    }, {
                        Log.e("dupa", "error2")
                        registerStateLiveData.value = false
                    }
                ))
        }
    }


}