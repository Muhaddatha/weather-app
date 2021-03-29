package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.ui.main.MainFragment
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.Response
import com.example.weatherapp.ui.main.MainViewModel

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    public lateinit var requestQueue : RequestQueue
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //instantiate the request queue
        requestQueue = Volley.newRequestQueue(this)

        //api url
        val url : String = "https://api.openweathermap.org/data/2.5/onecall?lat=42.3314&lon=-83.0458&exclude=minutely,hourly,alerts&units=imperial&appid="


        //create object request
        var jsonObjectRequest : JsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
         Response.Listener<JSONObject>(){
             //json object that we get back from the api call
             response ->

                 try {
                     viewModel.sendData(response) // send json response object to MainViewModel
                 }
                 catch(ex : JSONException) {
                     Log.e("JSON Error", ex.localizedMessage)
                 }
         },
        Response.ErrorListener(){
            error ->
                Log.e("JSON Error", error.localizedMessage)
        }) // end of JSON object request

        requestQueue.add(jsonObjectRequest)

    } // end of onCreate()
} // end of MainActivity