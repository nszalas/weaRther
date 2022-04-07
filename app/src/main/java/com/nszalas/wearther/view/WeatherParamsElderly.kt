package com.nszalas.wearther.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputEditText
import com.nszalas.wearther.R
import com.nszalas.wearther.viewmodel.WeatherParamsViewModel
import java.text.SimpleDateFormat


class WeatherParamsElderly : Fragment() {

    private lateinit var viewModel: WeatherParamsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.elderly_view,container,false)


        viewModel = ViewModelProvider(this).get(WeatherParamsViewModel::class.java)


        viewModel.all.observe(viewLifecycleOwner,{weather -> view.findViewById<TextView>(R.id.cardCityE).setText(weather.name.toString())})
        viewModel.all.observe(viewLifecycleOwner,{weather ->


            val dts = weather.sys.sunrise.toLong()*1000
            val ta = SimpleDateFormat("HH:mm").format(dts)
            view.findViewById<TextView>(R.id.cardDateTimeE).setText(ta.toString())})

        viewModel.all.observe(viewLifecycleOwner,{weather -> view.findViewById<TextView>(R.id.cardTemperatureE).setText(weather.main.temp.toInt().toString()+"°C")})
        viewModel.all.observe(viewLifecycleOwner,{weather -> view.findViewById<TextView>(R.id.cardShortDesE).setText(weather.weather[0].description.toString())})
        viewModel.all.observe(viewLifecycleOwner,{weather ->
            if(weather.weather[0].icon == "01d")
                view.findViewById<ImageView>(R.id.cardIconE).setImageResource(R.drawable.sun)
            else if(weather.weather[0].icon == "01n")
                view.findViewById<ImageView>(R.id.cardIconE).setImageResource(R.drawable.moon)
            else if(weather.weather[0].icon == "02d")
                view.findViewById<ImageView>(R.id.cardIconE).setImageResource(R.drawable.sun_cloud)
            else if(weather.weather[0].icon == "02n")
                view.findViewById<ImageView>(R.id.cardIconE).setImageResource(R.drawable.moon_cloud)
            else if(weather.weather[0].icon == "03d" || weather.weather[0].icon == "03n" || weather.weather[0].icon == "04d" || weather.weather[0].icon == "04n")
                view.findViewById<ImageView>(R.id.cardIconE).setImageResource(R.drawable.cl)
            else if(weather.weather[0].icon == "09d" || weather.weather[0].icon == "09n" || weather.weather[0].icon == "10d" || weather.weather[0].icon == "10n")
                view.findViewById<ImageView>(R.id.cardIconE).setImageResource(R.drawable.rain)
            else if(weather.weather[0].icon == "11d" || weather.weather[0].icon == "11n")
                view.findViewById<ImageView>(R.id.cardIconE).setImageResource(R.drawable.storm)
            else if(weather.weather[0].icon == "13d" || weather.weather[0].icon == "13n")
                view.findViewById<ImageView>(R.id.cardIconE).setImageResource(R.drawable.snow)
            else if(weather.weather[0].icon == "50d" || weather.weather[0].icon == "50n")
                view.findViewById<ImageView>(R.id.cardIconE).setImageResource(R.drawable.mist)


            //view.findViewById<ImageView>(R.id.cardIcon).setImageIcon()
        })

        viewModel.all.observe(viewLifecycleOwner,{weather -> view.findViewById<TextView>(R.id.cardPressureE).setText(weather.main.pressure.toString()+" hPa")})
        viewModel.all.observe(viewLifecycleOwner,{weather -> view.findViewById<ImageView>(R.id.cardPressureImageE).setImageResource(R.drawable.pressure_color)})
        viewModel.all.observe(viewLifecycleOwner,{weather -> view.findViewById<TextView>(R.id.cardWindSpeedE).setText(weather.wind.speed.toString()+" km/h")})
        viewModel.all.observe(viewLifecycleOwner,{weather -> view.findViewById<ImageView>(R.id.cardWindImageE).setImageResource(R.drawable.wind_color)})
        //viewModel.all.observe(viewLifecycleOwner,{weather -> view.findViewById<TextView>(R.id.cardHumidityE).setText(weather.main.humidity.toString()+"%") })
        //viewModel.all.observe(viewLifecycleOwner,{weather -> view.findViewById<ImageView>(R.id.cardHumidityImageE).setImageResource(R.drawable.humidity_color)})



        viewModel.all.observe(viewLifecycleOwner,{weather ->

            val timing = weather.sys.sunrise.toLong()*1000
            val t = SimpleDateFormat("HH:mm").format(timing)
            view.findViewById<TextView>(R.id.cardSunriseE).setText(t.toString())})
        viewModel.all.observe(viewLifecycleOwner,{weather -> view.findViewById<ImageView>(R.id.cardSunriseImageE).setImageResource(R.drawable.sunrise)})


        viewModel.all.observe(viewLifecycleOwner,{weather ->

            val timing = weather.sys.sunset.toLong()*1000
            val t = SimpleDateFormat("HH:mm").format(timing)
            view.findViewById<TextView>(R.id.cardSunsetE).setText(t.toString())})
        viewModel.all.observe(viewLifecycleOwner,{weather -> view.findViewById<ImageView>(R.id.cardSunsetImageE).setImageResource(R.drawable.sunset)})



        viewModel.postAll("Katowice")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        view.findViewById<Button>(R.id.searchButtonE).setOnClickListener {
            var cityname = view.findViewById<TextInputEditText>(R.id.inputCityE).text.toString()
            viewModel.postAll(cityname)
        }


        view.findViewById<MaterialToolbar>(R.id.topAppBarE).setOnMenuItemClickListener { menuItem ->

            when (menuItem.itemId) {
                R.id.normalView -> {
                    view.findNavController()
                        .navigate(R.id.action_weatherParamsElderly_to_weatherParamsLayout)
                    true
                }

                else -> false
            }

        }
    }

}