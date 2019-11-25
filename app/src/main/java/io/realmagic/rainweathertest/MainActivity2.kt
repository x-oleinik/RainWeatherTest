package io.realmagic.rainweathertest

import android.content.Context
import android.content.SharedPreferences
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

    lateinit var pref : SharedPreferences
    //
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    private val mActivity
        get() = (this as MainActivity)

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

        //Get Location !!!! SOOOOQA EBANAYA! I WANNA GET IT!
            Runnable {
                fusedLocationClient.lastLocation
                    .addOnSuccessListener {
                        Log.i("Location888", it.toString())
                    }

        }


    }

    override fun onBackPressed() {
        if (pref.getBoolean(WAS_REG, false)){

        }
    }
}
