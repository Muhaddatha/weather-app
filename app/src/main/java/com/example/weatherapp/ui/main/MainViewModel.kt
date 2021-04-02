package com.example.weatherapp.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import org.json.JSONArray
import org.json.JSONObject

class MainViewModel : ViewModel() {
    // current weather JSON object
    private var currentWeather : JSONObject? = null

    // json array of weather objects for the next 8 days
    private var daily : JSONArray? = null

    // Receive JSON response object from MainFragment
    fun sendData(response : JSONObject?) {
        Log.i("test JSON response in ViewModel", "response: " + response.toString())

        // get description of current and daily (7 day) weather
        currentWeather = response?.getJSONObject("current")
        daily = response?.getJSONArray("daily")

        Log.i("test JSON response in ViewModel", "current: " + currentWeather.toString())
        for(i in 0 .. 6){
           Log.i("test JSON response in ViewModel", "daily[$i]: " + daily?.getJSONObject(i).toString())
        }
    }

    // Returns current weather JSON object
    fun getCurrentWeather(): JSONObject? {
        return currentWeather
    }

    // Returns daily weather JSON array
    fun getDailyWeather(): JSONArray? {
        return daily
    }

} // end of MainViewModel