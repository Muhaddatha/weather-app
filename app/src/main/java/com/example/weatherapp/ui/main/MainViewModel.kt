package com.example.weatherapp.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import androidx.lifecycle.MutableLiveData

class MainViewModel : ViewModel() {

    // current weather json object
    //private lateinit var currentWeather : JSONObject
    private var currentWeatherLiveData : MutableLiveData<String> = MutableLiveData()
    private var currentWeather : String = ""

    // json array of weather objects for the next 8 days
    private lateinit var daily : JSONArray

    // Retrieves JSON response object from API call in MainActivity
    fun sendData(response : JSONObject) {
        //Log.i("JSON response in ViewModel", response.toString())

        try {
            // get description of current and daily (7 day) weather
            currentWeather = response.getJSONObject("current").toString()
            currentWeatherLiveData.value = response.getJSONObject("current").toString()

            daily = response.getJSONArray("daily")

            //Log.i("JSON response in ViewModel", "current: " + currentWeather.toString())

//            for(i in 0 .. 6){
//               // Log.i("JSON response in ViewModel", "daily[$i]: " + daily.getJSONObject(i).toString())
//            }

        }
        catch(ex : JSONException) {
           // Log.e("JSON Error in ViewModel", ex.localizedMessage)
        }
    }

    // Returns current weather JSON object
    fun getCurrentWeather(): MutableLiveData<String> {
//        if (currentWeather.toString() == "") {
//            return "empty"
//        }
//        else {
//            return currentWeather.toString()
//        }
        return currentWeatherLiveData

    }

    // Returns daily weather JSON array
    fun getDailyWeather(): JSONArray {
        return daily
    }

    fun setCurrentWeather() {
        currentWeatherLiveData.value = currentWeather
    }
}