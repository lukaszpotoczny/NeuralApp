package com.example.neuralapp.ui.houses

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.neuralapp.base.BaseViewModel
import com.example.neuralapp.data.model.House
import com.example.neuralapp.repository.HouseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HouseListViewModel @Inject constructor(
    private val houseRepository: HouseRepository
): BaseViewModel() {

    val houseListLiveData = MutableLiveData<List<House>>()
    private val houses = mutableListOf<House>()

    private val mockHouse = House(
        id = 123,
        0,
        0,
        0,
        0,
        0,
        0,
    )
    fun fakeList(){
        houses.clear()

        houses.add(mockHouse)
        houses.add(mockHouse)
        houses.add(mockHouse)
        houses.add(mockHouse)
        houses.add(mockHouse)
        houses.add(mockHouse)
        houses.add(mockHouse)

        houseListLiveData.value = houses
    }

    fun fetchHouses(){
        disposableObservables.add(
            houseRepository.getHouseList().subscribe(
                { response ->
                    Log.e("dupa", "ez")
                    houseListLiveData.value = response

                }, {
                    Log.e("dupa", "api sucks")
                }
            ))
    }

}