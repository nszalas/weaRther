package com.nszalas.wearther.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nszalas.wearther.model.WeatherParams
import com.nszalas.wearther.model.WeatherParamsRepository
import kotlinx.coroutines.launch

class WeatherParamsViewModel(): ViewModel() {

    val readAl: MutableLiveData<String> = MutableLiveData()
    val namecity: LiveData<String>
        get() {
            return readAl
        }

    var nameName = namecity

    val readAllParams: MutableLiveData<WeatherParams> = MutableLiveData()

    val all: LiveData<WeatherParams>

    get(){
        return readAllParams
    }

    var allAll = all

    fun postAll(cityname: String){

        //var t = getThatString(cityname)

        viewModelScope.launch {
            val weatherParams = WeatherParamsRepository.getAllParams(cityname)

            if (weatherParams!=null)
                readAllParams.value = weatherParams!!
        }
    }

    fun setDataWeatherParams(weatherParams: LiveData<WeatherParams>){
        this.allAll = weatherParams

    }

    fun getThatString(cityname:LiveData<String>){
        this.nameName = cityname
    }

    fun getWeather(city:String){

        viewModelScope.launch {
            val weather1 = WeatherParamsRepository.getAllParams(city)

        }
    }
}

