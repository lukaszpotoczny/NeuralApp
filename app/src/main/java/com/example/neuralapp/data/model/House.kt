package com.example.neuralapp.data.model

import java.io.Serializable

data class House(
    val id: Int,
    val price: Int?,
    val subClass: Int,
    val lotFrontage: Int,
    val lotArea: Int,
    val quality: Int,
    val condition: Int,
) : Serializable