package io.realmagic.rainweathertest

import android.content.Context
import android.content.SharedPreferences
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {

    val APP_PREFERENCES = "MySettings"
    val USER_NAME = "UserName"
    val WAS_REG = "RegisterCheck"
    val API_KEY = "217fca703f17d3de012dd3dc2aed2b4d"

    lateinit var pref : SharedPreferences
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var restClient: RestClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //Get SharedPreferences
        pref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        //Set Hello_Text connected to User_Name
        val username : String? = pref.getString(USER_NAME, getString(R.string.new_user))
        hi_user_tv.text = "Welcome, $username!"

        //Create location client
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        //Rest client
        restClient = RestClient()

        //Get Location
        fusedLocationClient.lastLocation
            .addOnSuccessListener {
                Log.i("Location888", it.toString())
                Log.i("Latitude", it.latitude.toString())
                Log.i("Longitude", it.longitude.toString())
               val latitude : Double = it.latitude
               val longitude : Double = it.longitude

                //Request weather
                Thread(Runnable {
                    val response = restClient.api.getWeather(latitude, longitude)
                    runOnUiThread {
                        location_tv.text = response.request().body.toString()
                        Log.i("Response888", response.request().body.toString())
                    }
                }).start()
        }





    }

    override fun onBackPressed() {
        if (pref.getBoolean(WAS_REG, false)){

        }
    }
}
