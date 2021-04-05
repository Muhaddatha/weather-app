package com.example.weatherapp.ui.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
//import com.android.volley.Request
import com.android.volley.RequestQueue
//import com.android.volley.Response
//import com.android.volley.toolbox.JsonObjectRequest
//import com.example.weatherapp.MainActivity
import okhttp3.OkHttpClient
import org.json.JSONException
import okhttp3.Request
import okhttp3.*
import java.io.IOException

class MainViewModel : ViewModel() {
    val client = OkHttpClient()
    //var contextTest : Context = (activity as MainActivity).getActivityContext()
    // current weather JSON object
    private var currentWeather : String? = null
    val url = "https://api.openweathermap.org/data/2.5/onecall?lat=42.3314&lon=-83.0458&exclude=minutely,hourly,alerts&units=imperial&appid="

    // json array of weather objects for the next 8 days
    private var daily : String? = null

    private lateinit var requestQueue : RequestQueue
    var resp: Response? = null // Public variable to access JSON object response outside of class

    // Receive JSON response object from MainFragment
    fun sendData(response : Response?) {

        Log.i("test JSON response in ViewModel", "response: " + response.toString())

        // get description of current and daily (7 day) weather

        currentWeather = response?.toString()
        daily = response?.toString()
//        currentWeather = response?.getJSONObject("current")
//        daily = response?.getJSONArray("daily")

//        Log.i("test JSON response in ViewModel", "current: " + currentWeather.toString())
//        for(i in 0 .. 6){
//           Log.i("test JSON response in ViewModel", "daily[$i]: " + daily?.getJSONObject(i).toString())
//        }
    }

    // Returns current weather JSON object
    fun getCurrentWeather(): String? {
        return currentWeather
    }

    // Returns daily weather JSON array
    fun getDailyWeather(): String? {
        return daily
    }

//    fun makeAPICall(): JSONObject? {
//        requestQueue = Volley.newRequestQueue(contextTest)
//
//        //api url
//
//        //create object request
//        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
//                Response.Listener<JSONObject> {
//                    //json object that we get back from the api call
//                    response ->
//
//                    try {
//                        Log.i("test", "response in MainViewModel: $response")
//                        resp = response // Make public copy of JSON object response
//                    }
//                    catch(ex : JSONException) {
//                        Log.e("JSON Error", ex.toString())
//                    }
//                },
//                Response.ErrorListener {
//                    error ->
//                    Log.e("JSON Error", error.toString())
//                }) // end of JSON object request
//
//        requestQueue.add(jsonObjectRequest)
//
//        return resp
//    }


    fun makeAPICall(): Response?{
        val request = Request.Builder().url(url).build()

//        client.newCall(request).enqueue(object : Callback {
//
//            override fun onFailure(call: Call, e: IOException) {
//                e.printStackTrace()
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                response.use{
//                    if(!response.isSuccessful) throw IOException("Unexpected code $response")
//
////                    for((name, value) in response.headers){
////
////                    }
//
//                    println("Body " + response.toString())
//                    Log.i("test in makeAPICall httpok", response.toString())
//                    Log.i("test message in makeAPICall httpok", response.message())
//                    Log.i("test body in makeAPICall httpok", response.body().toString())
//                    Log.i("test headers in makeAPICall httpok", response.headers().toString())
//                    Log.i("test body string in makeAPICall httpok", response.body()!!.string())
//
//                    currentWeather = response.body()!!.string()
//                }
//            }
//        })

        client.newCall(request).execute().use { response ->
            if(!response.isSuccessful) throw IOException("Unexpected code $response")

            resp = response
        }


        return resp
    }

} // end of MainViewModel