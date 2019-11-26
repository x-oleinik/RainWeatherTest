package io.realmagic.rainweathertest

import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    companion object{
        // on launch
        const val BASE_URL : String = "http://api.openweathermap.org"
        const val GET_WEATHER = "/data/2.5/weather?lat={latitude}&lon={longitude}&units=metric&APPID=217fca703f17d3de012dd3dc2aed2b4d"
    }

    @GET
    fun getWeather(@Path("latitude") latitude : Double, @Path("longitude") longitude : Double) : retrofit2.Call<WeatherData>
}