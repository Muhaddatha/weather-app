package com.example.weatherapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapp.MainActivity
import com.example.weatherapp.R
import kotlinx.android.synthetic.main.main_fragment.*
import org.json.JSONArray
import org.json.JSONObject

class MainFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private var currentWeather: JSONObject? = null
    private var weatherArray: JSONArray? = null

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java) // Get reference to MainViewModel

        Log.i("test", "data in MainFragment: " + (activity as MainActivity).resp.toString())
        viewModel.sendData((activity as MainActivity).resp) // Send JSON response object to MainViewModel for parsing
        Log.i("test", "data2 in MainFragment: " + viewModel.getCurrentWeather().toString())

        currentWeather = viewModel.getCurrentWeather() // Get currentWeather JSON object

        // Parse JSON object and set GUI text fields
        locationTextView.text = "Location: " + (activity as MainActivity).resp?.get("timezone").toString()
        updatedAtTextView.text = "Updated at: " + currentWeather?.get("dt").toString()
        currentWeatherTextView.text = "Temperature: " + currentWeather?.get("temp").toString() + "°F"
        feelsLikeTextView.text = "Feels like: " + currentWeather?.get("feels_like").toString() + "°F"
        sunriseTextView.text = currentWeather?.get("sunrise").toString()
        sunsetTextView.text = currentWeather?.get("sunset").toString()
        humidityTextView.text = currentWeather?.get("humidity").toString() + "%"
        windSpeedTextView.text = currentWeather?.get("wind_speed").toString() + " mph"

        weatherArray = currentWeather?.getJSONArray("weather") // Get weatherArray JSON array
        Log.i("test weatherArray in MainFragment", weatherArray?.getJSONObject(0)?.get("icon").toString())

        // Set GUI icon based on "icon" field in weatherArray
        when(weatherArray?.getJSONObject(0)?.get("icon").toString()) {
            "01d" -> iconImageView.setImageResource(R.drawable._01d)
            "01n" -> iconImageView.setImageResource(R.drawable._01n)
            "02d" -> iconImageView.setImageResource(R.drawable._02d)
            "02n" -> iconImageView.setImageResource(R.drawable._02n)
            "03d" -> iconImageView.setImageResource(R.drawable._03d)
            "03n" -> iconImageView.setImageResource(R.drawable._03n)
            "04d" -> iconImageView.setImageResource(R.drawable._04d)
            "04n" -> iconImageView.setImageResource(R.drawable._04n)
            "09d" -> iconImageView.setImageResource(R.drawable._09d)
            "09n" -> iconImageView.setImageResource(R.drawable._09n)
            "10d" -> iconImageView.setImageResource(R.drawable._10d)
            "10n" -> iconImageView.setImageResource(R.drawable._10n)
            "11d" -> iconImageView.setImageResource(R.drawable._11d)
            "11n" -> iconImageView.setImageResource(R.drawable._11n)
            "13d" -> iconImageView.setImageResource(R.drawable._13d)
            "13n" -> iconImageView.setImageResource(R.drawable._13n)
            "50d" -> iconImageView.setImageResource(R.drawable._50d)
            "50n" -> iconImageView.setImageResource(R.drawable._50n)
        }

//        sevenDayTab.setOnClickListener { // Change to SecondFragment from MainFragment on button click
//            (activity as MainActivity).changeFragment(id, "mainFragment")
//        }

    } // end of onActivityCreated

    fun handleSevenDayTabClick(view: View) {
        (activity as MainActivity).changeFragment(id, "mainFragment")
    }

} // end of MainFragment