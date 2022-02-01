package com.example.neuralapp.repository

import com.example.neuralapp.data.db.User
import com.example.neuralapp.data.db.UserDao
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {

    fun getUsers(): Observable<List<User>?> {
        return Observable.fromCallable {
            userDao.getAll()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun findUser(username: String): Observable<User?> {
        return Observable.fromCallable {
            userDao.findByUsername(username)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun addUser(user: User): Observable<Unit> {
        return Observable.fromCallable {
            userDao.insertAll(user)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}