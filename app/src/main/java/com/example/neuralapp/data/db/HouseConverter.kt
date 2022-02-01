package com.example.neuralapp.data.db

import androidx.room.TypeConverter
import com.example.neuralapp.data.model.HouseList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class HouseConverter {
    @TypeConverter
    fun stringToProduct(json: String?): List<HouseList?>? {
        val type: Type = object : TypeToken<List<HouseList?>?>() {}.type
        return Gson().fromJson<List<HouseList?>?>(json, type)
    }

    @TypeConverter
    fun productToString(product: List<HouseList>?): String? {
        return Gson().toJson(product)
    }
}