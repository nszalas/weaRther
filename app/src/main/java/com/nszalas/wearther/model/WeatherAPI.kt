package com.nszalas.wearther.model

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query


//"https://api.openweathermap.org/data/2.5/weather?q=Katowice&APPID=98ed72d7190f406b2e83af52b3dc15f7"

interface WeatherAPI {

    @GET("data/2.5/weather?&units=metric&APPID=98ed72d7190f406b2e83af52b3dc15f7")
    fun getAllParams(
        @Query("q") name:String
    ): Call<WeatherParams>


}