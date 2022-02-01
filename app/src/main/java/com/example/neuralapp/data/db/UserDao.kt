package com.example.neuralapp.data.db

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): List<User>

    @Query("SELECT * FROM users WHERE username LIKE :username")
    fun findByUsername(username: String): User?

    @Insert
    fun insertAll(vararg user: User)

    @Delete
    fun delete(user: User)
}