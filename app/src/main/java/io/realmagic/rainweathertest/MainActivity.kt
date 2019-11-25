package io.realmagic.rainweathertest

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val APP_PREFERENCES = "MySettings"
    private val USER_NAME = "UserName"
    private val WAS_REG = "RegisterCheck"

    private lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Creating SharedPreferences
        pref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        //Checking for the previous registration, if was - open Main Activity
        checkReg()

        //Handling Start! button after entering the name
        register_button.setOnClickListener {
            //Handling if the name is empty or > then 16 chars
            if (user_name.text.isEmpty() || user_name.text.length > 16){
                warning_text.text = getString(R.string.warn_text_name)
            } else {
            //Handling if everything is OK: save USER_NAME + open the next Activity
                val editor = pref.edit()
                editor.putString(USER_NAME, user_name.text.toString())
                    .putBoolean(WAS_REG, true)
                    .apply()
                openMainActivity()
            }
        }
    }

    private fun checkReg(){
        val wasReg: Boolean = pref.getBoolean(WAS_REG, false)
        if (wasReg){
            openMainActivity()
        }
    }
    private fun openMainActivity(){
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
    }
}
