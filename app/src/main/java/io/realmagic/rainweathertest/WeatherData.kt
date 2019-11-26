package io.realmagic.rainweathertest

data class WeatherData (
    var mainData: MainData
)

data class MainData (
    var temp : Double,
    var pressure: Double,
    var humidity : Double,
    var temp_min : Double,
    var temp_max : Double
)
