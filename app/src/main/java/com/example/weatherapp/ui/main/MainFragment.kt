package com.example.weatherapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import com.example.weatherapp.MainActivity
import com.example.weatherapp.R
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {
    private lateinit var viewModel: MainViewModel

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

        sevenDayWeatherButton.setOnClickListener {
            handleSevenDayTab(it)
        }


    } // end of onActivityCreated

    private fun handleSevenDayTab(view : View){
        Log.i("test", "insideHandleSevenDayTab click event")
        (activity as MainActivity).changeFragment(this.id, "mainFragment")

    }
} // end of MainFragment