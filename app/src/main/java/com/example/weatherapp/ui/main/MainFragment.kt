package com.example.weatherapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.MainActivity
import com.example.weatherapp.R
import kotlinx.android.synthetic.main.main_fragment.*
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import java.lang.IllegalStateException

class MainFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
//    private lateinit var requestQueue : RequestQueue
//    var resp: JSONObject? = null // Public variable to access JSON object response outside of class

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //instantiate the request queue
//        requestQueue = Volley.newRequestQueue(this.requireActivity())
//
//        //api url
//        val url = "https://api.openweathermap.org/data/2.5/onecall?lat=42.3314&lon=-83.0458&exclude=minutely,hourly,alerts&units=imperial&appid=effd11265cd5a93848a2b781b9ed5c5c"
//
//        //create object request
//        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
//            Response.Listener<JSONObject> {
//                //json object that we get back from the api call
//                    response ->
//
//                try {
//                    Log.i("test", "response in MainActivity: $response")
//                    resp = response // Make public copy of JSON object response
//                }
//                catch(ex : JSONException) {
//                    Log.e("JSON Error", ex.toString())
//                }
//
//                //                    if (savedInstanceState == null) { // Moved fragment replacement to occur after API call
//                //                        supportFragmentManager.beginTransaction()
//                //                                .replace(R.id.container, MainFragment.newInstance())
//                //                                .commitNow()
//                //                        Log.i("test", "Replace with fragment in MainActivity")
//                //                    }
//            },
//            Response.ErrorListener {
//                    error ->
//                Log.e("JSON Error", error.toString())
//            }) // end of JSON object request
//
//        requestQueue.add(jsonObjectRequest)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java) // Get reference to MainViewModel

        Log.i("test", "data in MainFragment: " + (activity as MainActivity).resp.toString())
        viewModel.sendData((activity as MainActivity).resp) // Send JSON response object to MainViewModel for parsing
        Log.i("test", "data2 in MainFragment: " + viewModel.getCurrentWeather().toString())

        if(viewModel.getCurrentWeather() == null){
            message.text = ""
        }
        else{
            message.text = viewModel.getCurrentWeather().toString()
        }
        if(viewModel.getDailyWeather() == null){
            textView.text = ""
        }
        else {
            textView.text = viewModel.getDailyWeather().toString()
        }

        if (message.text == "") {
            ourButton.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.mainToSecond)
            }
        }
//        try{
//            ourButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.mainToSecond, null))
//        }catch (e: Exception){
//            Log.i("test", e.toString())
//        }

    } // end of onActivityCreated



} // end of MainFragment