package com.example.neuralapp.data.rest

import com.example.neuralapp.data.model.House
import com.example.neuralapp.data.request.HouseRequest
import com.example.neuralapp.data.response.HouseResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    //TODO set url - for example "/api/houses"
    @POST("url")
    fun calculateHousePrice(@Body houseRequest: HouseRequest): Observable<HouseResponse>

    //TODO set url
    @GET("url")
    fun getHouseList(): Observable<List<House>>
}