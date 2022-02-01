package com.example.neuralapp.repository

import com.example.neuralapp.data.model.House
import com.example.neuralapp.data.request.HouseRequest
import com.example.neuralapp.data.response.HouseResponse
import com.example.neuralapp.data.rest.ApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HouseRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun getHousePrice(houseRequest: HouseRequest): Observable<HouseResponse> =
        apiService.calculateHousePrice(houseRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getHouseList(): Observable<List<House>> =
        apiService.getHouseList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}