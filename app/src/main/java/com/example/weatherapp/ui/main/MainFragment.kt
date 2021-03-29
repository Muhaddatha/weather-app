package com.example.weatherapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapp.R
import kotlinx.android.synthetic.main.main_fragment.*
import org.json.JSONException
import org.json.JSONObject
import java.lang.ClassCastException
import kotlin.Exception

class MainFragment : Fragment() {
    // comment test
    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        var currentWeather : JSONObject

//        try {
////            currentWeather = viewModel.getCurrentWeather()
////            message.text = currentWeather.toString() // Not working
//        }
//        catch (ex: Exception){ //changed from NullPointerException to Exception
//            //Log.i("failed", ex.localizedMessage)
//
//            message.text = "Null pointer" // Not executing
//        }

        // hElLoOoOoO

        //textView.text = viewModel.getDailyWeather().toString()
    }

}