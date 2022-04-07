package com.nszalas.wearther.model

import com.nszalas.wearther.viewmodel.WeatherParamsViewModel
import retrofit2.awaitResponse



class WeatherParamsRepository {



    companion object{

        suspend fun getAllParams(cityname:String): WeatherParams?{
            return RetrofitInstance.api.getAllParams(cityname).awaitResponse().body()
        }
    }
}