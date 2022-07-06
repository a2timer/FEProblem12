package com.cartelnetwork.feproblem1.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.cartelnetwork.feproblem1.BaseViewModel.MainRepository
import com.cartelnetwork.feproblem1.api.Resource
import com.cartelnetwork.feproblem1.model.FindRequestBody
import com.cartelnetwork.feproblem1.model.Planets
import com.cartelnetwork.feproblem1.model.Vehicles
import kotlinx.coroutines.Dispatchers


class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {
     lateinit var token : String
      var planetsDetailsList = MutableLiveData<ArrayList<Planets>>()
     var vehiclesDetailsList = MutableLiveData<ArrayList<Vehicles>>()
    var planetsDetailsListTwo = MutableLiveData<ArrayList<Planets>>()
    var planetsDetailsListThree = MutableLiveData<ArrayList<Planets>>()
    var planetsDetailsListFour = MutableLiveData<ArrayList<Planets>>()
    var totalTakenDestinationOne : Int = 0
    var totalTakenDestinationTwo : Int = 0

    var totalTakenDestinationThree: Int = 0
    var totalTakenDestinationFour : Int = 0

    var destinationOnePlanet = MutableLiveData<Int>()
    var destinationTwoPlanet = MutableLiveData<Int>()
    var destinationThreePlanet = MutableLiveData<Int>()
    var destinationFourPlanet = MutableLiveData<Int>()
    var totalTakenTime = MutableLiveData<Int>()


    fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getUsers()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getPlanets() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getPlanet()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
    fun getVehicle() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getVehicle()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
    fun find(request: FindRequestBody) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.find(request)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}