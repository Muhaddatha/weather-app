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
import android.net.Uri
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.main_activity.*

import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity(), SecondFragment.OnFragmentInteractionListener {

    private lateinit var requestQueue : RequestQueue
    var resp: JSONObject? = null // Public variable to access JSON object response outside of class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        //instantiate the request queue
        requestQueue = Volley.newRequestQueue(this)

        //api url
        val url = "https://api.openweathermap.org/data/2.5/onecall?lat=42.3314&lon=-83.0458&exclude=minutely,hourly,alerts&units=imperial&appid="

        //create object request
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener<JSONObject> {
                    //json object that we get back from the api call
                    response ->

                    try {
                        Log.i("test", "response in MainActivity: $response")
                        resp = response // Make public copy of JSON object response
                    }
                    catch(ex : JSONException) {
                        Log.e("JSON Error", ex.toString())
                    }

                    if (savedInstanceState == null) { // Moved fragment replacement to occur after API call
                        supportFragmentManager.beginTransaction()
                                .replace(R.id.container, MainFragment.newInstance())
                                .commitNow()
                        Log.i("test", "Replace with fragment in MainActivity")
                    }
                },
                Response.ErrorListener {
                    error ->
                    Log.e("JSON Error", error.toString())
                }) // end of JSON object request

        requestQueue.add(jsonObjectRequest)

//        button.setOnClickListener {
//            button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.mainToSecond, null))
//        }

    } // end of onCreate()

    override fun onFragmentInteraction(uri: Uri){

    }
} // end of MainActivity