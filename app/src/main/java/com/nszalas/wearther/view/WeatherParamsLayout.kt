package com.nszalas.wearther.view

import android.app.AlertDialog
import android.content.ClipData
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.appbar.AppBarLayout
import com.nszalas.wearther.R
import com.nszalas.wearther.model.Weather
import com.nszalas.wearther.viewmodel.WeatherParamsViewModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import android.content.DialogInterface
import android.text.InputType
import android.view.*
import android.widget.*
import androidx.*
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar

import com.google.android.material.textfield.TextInputEditText
import retrofit2.http.GET


class WeatherParamsLayout : Fragment() {

    private lateinit var viewModel: WeatherParamsViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.normal_view,container,false)


        viewModel = ViewModelProvider(this).get(WeatherParamsViewModel::class.java)


        viewModel.all.observe(viewLifecycleOwner,{weather -> view.findViewById<TextView>(R.id.cardCity).setText(weather.name.toString())})
        viewModel.all.observe(viewLifecycleOwner,{weather ->


            val dts = weather.sys.sunrise.toLong()*1000
            val ta = SimpleDateFormat("HH:mm").format(dts)
            view.findViewById<TextView>(R.id.cardDateTime).setText(ta.toString())})

        viewModel.all.observe(viewLifecycleOwner,{weather -> view.findViewById<TextView>(R.id.cardTemperature).setText(weather.main.temp.toInt().toString()+"°C")})
        viewModel.all.observe(viewLifecycleOwner,{weather -> view.findViewById<TextView>(R.id.cardShortDes).setText(weather.weather[0].description.toString())})
        viewModel.all.observe(viewLifecycleOwner,{weather ->
            if(weather.weather[0].icon == "01d")
                view.findViewById<ImageView>(R.id.cardIcon).setImageResource(R.drawable.sun)
            else if(weather.weather[0].icon == "01n")
                view.findViewById<ImageView>(R.id.cardIcon).setImageResource(R.drawable.moon)
            else if(weather.weather[0].icon == "02d")
                view.findViewById<ImageView>(R.id.cardIcon).setImageResource(R.drawable.sun_cloud)
            else if(weather.weather[0].icon == "02n")
                view.findViewById<ImageView>(R.id.cardIcon).setImageResource(R.drawable.moon_cloud)
            else if(weather.weather[0].icon == "03d" || weather.weather[0].icon == "03n" || weather.weather[0].icon == "04d" || weather.weather[0].icon == "04n")
                view.findViewById<ImageView>(R.id.cardIcon).setImageResource(R.drawable.cl)
            else if(weather.weather[0].icon == "09d" || weather.weather[0].icon == "09n" || weather.weather[0].icon == "10d" || weather.weather[0].icon == "10n")
                view.findViewById<ImageView>(R.id.cardIcon).setImageResource(R.drawable.rain)
            else if(weather.weather[0].icon == "11d" || weather.weather[0].icon == "11n")
                view.findViewById<ImageView>(R.id.cardIcon).setImageResource(R.drawable.storm)
            else if(weather.weather[0].icon == "13d" || weather.weather[0].icon == "13n")
                view.findViewById<ImageView>(R.id.cardIcon).setImageResource(R.drawable.snow)
            else if(weather.weather[0].icon == "50d" || weather.weather[0].icon == "50n")
                view.findViewById<ImageView>(R.id.cardIcon).setImageResource(R.drawable.mist)


            //view.findViewById<ImageView>(R.id.cardIcon).setImageIcon()
        })

        viewModel.all.observe(viewLifecycleOwner,{weather -> view.findViewById<TextView>(R.id.cardPressure).setText(weather.main.pressure.toString()+" hPa")})
        viewModel.all.observe(viewLifecycleOwner,{weather -> view.findViewById<ImageView>(R.id.cardPressureImage).setImageResource(R.drawable.pressure_color)})
        viewModel.all.observe(viewLifecycleOwner,{weather -> view.findViewById<TextView>(R.id.cardWindSpeed).setText(weather.wind.speed.toString()+" km/h")})
        viewModel.all.observe(viewLifecycleOwner,{weather -> view.findViewById<ImageView>(R.id.cardWindImage).setImageResource(R.drawable.wind_color)})
        viewModel.all.observe(viewLifecycleOwner,{weather -> view.findViewById<TextView>(R.id.cardHumidity).setText(weather.main.humidity.toString()+"%") })
        viewModel.all.observe(viewLifecycleOwner,{weather -> view.findViewById<ImageView>(R.id.cardHumidityImage).setImageResource(R.drawable.humidity_color)})



        viewModel.all.observe(viewLifecycleOwner,{weather ->

            val timing = weather.sys.sunrise.toLong()*1000
            val t = SimpleDateFormat("HH:mm").format(timing)
            view.findViewById<TextView>(R.id.cardSunrise).setText(t.toString())})
        viewModel.all.observe(viewLifecycleOwner,{weather -> view.findViewById<ImageView>(R.id.cardSunriseImage).setImageResource(R.drawable.sunrise)})


        viewModel.all.observe(viewLifecycleOwner,{weather ->

            val timing = weather.sys.sunset.toLong()*1000
            val t = SimpleDateFormat("HH:mm").format(timing)
            view.findViewById<TextView>(R.id.cardSunset).setText(t.toString())})
        viewModel.all.observe(viewLifecycleOwner,{weather -> view.findViewById<ImageView>(R.id.cardSunsetImage).setImageResource(R.drawable.sunset)})



        viewModel.postAll("Katowice")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        view.findViewById<Button>(R.id.searchButton).setOnClickListener {
            var cityname = view.findViewById<TextInputEditText>(R.id.inputCity).text.toString()
            viewModel.postAll(cityname)
        }


        view.findViewById<MaterialToolbar>(R.id.topAppBar).setOnMenuItemClickListener { menuItem ->

            when (menuItem.itemId) {
                R.id.elderlyView -> {
                    view.findNavController().navigate(R.id.action_weatherParamsLayout_to_weatherParamsElderly)
                    true
                }

                else -> false
            }

        }

        //MenuItem.OnMenuItemClickListener { menuItem ->

            //when (menuItem.itemId) {
             //  R.id.elderlyView -> {
              //     changeDisplayAction(view,false)
              //     true
               // }

              // else -> false
           // }

       // }




        ///view.findViewById<MenuItem>(R.id.elderlyView).setOnClickListener {
           // view.findNavController().navigate(R.id.action_weatherParamsLayout_to_weatherParamsElderly)
        //}


        //view.findViewById<MenuItem>(R.id.elderlyView).OnMenuItemClickListener
        //{
          //  view.findNavController().navigate(R.id.action_weatherParamsLayout_to_weatherParamsElderly)
       // }


            //view.findNavController().navigate(R.id.action_weatherParamsLayout_to_weatherParamsElderly)  ) }





    }



    protected fun changeDisplayAction(view: View, isElderlyMode: Boolean) {
        if (isElderlyMode) view.findNavController().navigate(R.id.action_weatherParamsElderly_to_weatherParamsLayout)
        else view.findNavController().navigate(R.id.action_weatherParamsLayout_to_weatherParamsElderly)
    }


}