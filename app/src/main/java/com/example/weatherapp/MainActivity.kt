package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.weatherapp.ui.main.MainFragment
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.Response

import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var requestQueue : RequestQueue
    var resp: JSONObject? = null // Public variable to access JSON object response outside of class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        //instantiate the request queue
        requestQueue = Volley.newRequestQueue(this)

        //api url
        val url = "https://api.openweathermap.org/data/2.5/onecall?lat=42.3314&lon=-83.0458&exclude=minutely,hourly,alerts&units=imperial&appid=effd11265cd5a93848a2b781b9ed5c5c"

        //create object request
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener<JSONObject> {
                    //JSON object that we get back from the API call
                    response ->

                    try {
                        Log.i("test", "response in MainActivity: $response")


                        //val currentMoment: Instant = Clock.System.now()
                        resp = response // Make public copy of JSON object response
                    }
                    catch(ex : JSONException) {
                        Log.e("JSON Error", ex.toString())
                    }

                    if (savedInstanceState == null) { // Moved fragment replacement to occur after API call
                        supportFragmentManager.beginTransaction()
                                .replace(R.id.container, MainFragment.newInstance(), "mainFragment")
                                .commitNow()
                        Log.i("test", "Replace with initial fragment in MainActivity")
                    }
                },
                Response.ErrorListener {
                    error ->
                    Log.e("JSON Error", error.toString())
                }) // end of JSON object request

        requestQueue.add(jsonObjectRequest)

    } // end of onCreate()

    // Changes previous fragment with destination fragment
    fun changeFragment(prevFragmentId: Int, prevFragmentName: String) {
        when (prevFragmentName) {
            "mainFragment" -> { // Change from MainFragment to SecondFragment
                supportFragmentManager.beginTransaction()
                        .replace(prevFragmentId, SecondFragment.newInstance("p1", "p2"), "secondFragment")
                        .addToBackStack("null")
                        .commit()
            }
            "secondFragment" -> { // Change from SecondFragment to MainFragment
                supportFragmentManager.beginTransaction()
                        .replace(prevFragmentId, MainFragment.newInstance(), "mainFragment")
                        .addToBackStack("null")
                        .commit()
            }
        }
    }

} // end of MainActivity