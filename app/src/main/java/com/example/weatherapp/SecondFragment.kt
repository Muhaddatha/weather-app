package com.example.weatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.main_fragment.*
import org.json.JSONArray

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var dailyWeather: JSONArray? = null
    private var dailyWeatherArray: JSONArray? = null
    private lateinit var dailyIcons : List<ImageView>

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    // Added to allow daily weather data display
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dailyIcons = listOf<ImageView>(day0Icon, day1Icon, day2Icon, day3Icon, day4Icon, day5Icon, day6Icon) // List of daily weather icons

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java) // Get reference to MainViewModel

        Log.i("test", "data in SecondFragment: " + (activity as MainActivity).resp.toString())
        viewModel.sendData((activity as MainActivity).resp) // Send JSON response object to MainViewModel for parsing
        Log.i("test", "data2 in SecondFragment: " + viewModel.getDailyWeather().toString())

        dailyWeather = viewModel.getDailyWeather() // Get dailyWeather JSON array

        // Parse JSON object and set GUI text fields
        Log.i("test", "dailyWeather in SecondFragment: " + dailyWeather?.getJSONObject(0)?.getJSONObject("temp")?.get("day"))
        day0DayTemp.text = "Day:\n" + dailyWeather?.getJSONObject(0)?.getJSONObject("temp")?.get("day").toString() + "°F"
        day0NightTemp.text = "Night:\n" + dailyWeather?.getJSONObject(0)?.getJSONObject("temp")?.get("night").toString() + "°F"
        day1DayTemp.text = "Day:\n" + dailyWeather?.getJSONObject(1)?.getJSONObject("temp")?.get("day").toString() + "°F"
        day1NightTemp.text = "Night:\n" + dailyWeather?.getJSONObject(1)?.getJSONObject("temp")?.get("night").toString() + "°F"
        day2DayTemp.text = "Day:\n" + dailyWeather?.getJSONObject(2)?.getJSONObject("temp")?.get("day").toString() + "°F"
        day2NightTemp.text = "Night:\n" + dailyWeather?.getJSONObject(2)?.getJSONObject("temp")?.get("night").toString() + "°F"
        day3DayTemp.text = "Day:\n" + dailyWeather?.getJSONObject(3)?.getJSONObject("temp")?.get("day").toString() + "°F"
        day3NightTemp.text = "Night:\n" + dailyWeather?.getJSONObject(3)?.getJSONObject("temp")?.get("night").toString() + "°F"
        day4DayTemp.text = "Day:\n" + dailyWeather?.getJSONObject(4)?.getJSONObject("temp")?.get("day").toString() + "°F"
        day4NightTemp.text = "Night:\n" + dailyWeather?.getJSONObject(4)?.getJSONObject("temp")?.get("night").toString() + "°F"
        day5DayTemp.text = "Day:\n" + dailyWeather?.getJSONObject(5)?.getJSONObject("temp")?.get("day").toString() + "°F"
        day5NightTemp.text = "Night:\n" + dailyWeather?.getJSONObject(5)?.getJSONObject("temp")?.get("night").toString() + "°F"
        day6DayTemp.text = "Day:\n" + dailyWeather?.getJSONObject(6)?.getJSONObject("temp")?.get("day").toString() + "°F"
        day6NightTemp.text = "Night:\n" + dailyWeather?.getJSONObject(6)?.getJSONObject("temp")?.get("night").toString() + "°F"

        for(i in 0 .. 6) { // Set weather icon for each day of week
            dailyWeatherArray = dailyWeather?.getJSONObject(i)?.getJSONArray("weather") // Get weatherArray JSON array at index i of dailyWeather
            Log.i("test dailyWeather[$i] icon in SecondFragment", dailyWeatherArray?.getJSONObject(0)?.get("icon").toString())

            // Set GUI icon based on "icon" field in weatherArray
            when (dailyWeatherArray?.getJSONObject(0)?.get("icon").toString()) {
                "01d" -> dailyIcons[i].setImageResource(R.drawable._01d)
                "01n" -> dailyIcons[i].setImageResource(R.drawable._01n)
                "02d" -> dailyIcons[i].setImageResource(R.drawable._02d)
                "02n" -> dailyIcons[i].setImageResource(R.drawable._02n)
                "03d" -> dailyIcons[i].setImageResource(R.drawable._03d)
                "03n" -> dailyIcons[i].setImageResource(R.drawable._03n)
                "04d" -> dailyIcons[i].setImageResource(R.drawable._04d)
                "04n" -> dailyIcons[i].setImageResource(R.drawable._04n)
                "09d" -> dailyIcons[i].setImageResource(R.drawable._09d)
                "09n" -> dailyIcons[i].setImageResource(R.drawable._09n)
                "10d" -> dailyIcons[i].setImageResource(R.drawable._10d)
                "10n" -> dailyIcons[i].setImageResource(R.drawable._10n)
                "11d" -> dailyIcons[i].setImageResource(R.drawable._11d)
                "11n" -> dailyIcons[i].setImageResource(R.drawable._11n)
                "13d" -> dailyIcons[i].setImageResource(R.drawable._13d)
                "13n" -> dailyIcons[i].setImageResource(R.drawable._13n)
                "50d" -> dailyIcons[i].setImageResource(R.drawable._50d)
                "50n" -> dailyIcons[i].setImageResource(R.drawable._50n)
            }
        }

        currentWeatherTab.setOnClickListener {// Change to MainFragment from SecondFragment on button click
            (activity as MainActivity).changeFragment(id, "secondFragment")
        }
    } // end of onActivityCreated()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SecondFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                SecondFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

} // end of SecondFragment