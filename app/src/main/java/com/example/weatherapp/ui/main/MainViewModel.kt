package com.example.weatherapp.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainViewModel : ViewModel() {
    //hello

    // current weather json object
    private lateinit var currentWeather : JSONObject

    // json array of weather objects for the next 8 days
    private lateinit var daily : JSONArray

    // Retrieves JSON response object from API call in MainActivity
    fun sendData(response : JSONObject) {
        Log.i("JSON response in ViewModel", response.toString())

        try {
            // get description of current and daily (7 day) weather
            currentWeather = response.getJSONObject("current")
            daily = response.getJSONArray("daily")

            Log.i("JSON response in ViewModel", "current: " + currentWeather.toString())

            for(i in 0 .. 6){
                Log.i("JSON response in ViewModel", "daily[$i]: " + daily.getJSONObject(i).toString())
            }

        }
        catch(ex : JSONException) {
            Log.e("JSON Error in ViewModel", ex.localizedMessage)
        }
    }

    // Returns current weather JSON object
    fun getCurrentWeather(): JSONObject {
        return currentWeather
    }

    // Returns daily weather JSON array
    fun getDailyWeather(): JSONArray {
        return daily
    }
}