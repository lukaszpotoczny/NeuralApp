package com.example.neuralapp.ui.add

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.neuralapp.base.BaseViewModel
import com.example.neuralapp.data.request.HouseRequest
import com.example.neuralapp.repository.HouseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddHouseViewModel @Inject constructor(
    private val houseRepository: HouseRepository
): BaseViewModel() {

    val priceLiveData = MutableLiveData<String>()

    fun calculateHousePrice(
        bedrooms: Int,
        surface: Int,
        outsideArea: Int,
        condition: Int,
        quality: Int,
    ){
        val houseRequest = HouseRequest(
            subClass = if (bedrooms <= 10) bedrooms * 10 else 100,
            lotFrontage = surface,
            lotArea = outsideArea,
            quality = quality,
            condition = condition
        )

        disposableObservables.add(
            houseRepository.getHousePrice(houseRequest).subscribe(
                { response ->
                    Log.e("dupa", "ez")
                    priceLiveData.value = response.price

                }, {
                    Log.e("dupa", "api sucks")
                }
            ))
    }
}