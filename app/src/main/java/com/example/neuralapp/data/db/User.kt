package com.example.neuralapp.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.neuralapp.data.model.HouseList

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "username")
    var username: String,

    @ColumnInfo(name = "password")
    var password: String,
)