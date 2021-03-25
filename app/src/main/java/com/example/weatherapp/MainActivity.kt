package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.weatherapp.ui.main.MainFragment
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.Response

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var requestQueue : RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }

        //instantiate the request queue
        requestQueue = Volley.newRequestQueue(this)

        //api url
        var url : String = "https://google.com"

        //create object request
        var jsonObjectRequest : JsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
         Response.Listener<JSONObject>(){

             fun onResponse(response : JSONObject){ // REMOVED OVERRIDE
                 //this prints the whole string
                 //Log.i("JSON response, response.toString())

                 try {
                     // get description of weather
                     var weather : JSONArray = response.getJSONArray("weather")

                     // since it's one day of weather,
                     // there's one object in the array
                     var currentWeather : JSONObject = weather.getJSONObject(0)

                     var id : Int = currentWeather.getInt("id")
                     var mainWeather : String = currentWeather.getString("main")
                     var description : String = currentWeather.getString("description")

                     Log.i("JSON info", "ID: $id")
                     Log.i("JSON info", "main weather: $mainWeather")
                     Log.i("JSON info", "Description: $description")
                 }
                 catch(ex : JSONException) {
                     Log.e("JSON Error", ex.localizedMessage)
                 }
             }

         },
        Response.ErrorListener(){
            fun onErrorResponse(error : VolleyError) { // REMOVED OVERRIDE
                Log.e("JSON Error", error.localizedMessage)
            }
        }) // end of JSON object request

        requestQueue.add(jsonObjectRequest)

    } // end of onCreate()
} // end of MainActivity