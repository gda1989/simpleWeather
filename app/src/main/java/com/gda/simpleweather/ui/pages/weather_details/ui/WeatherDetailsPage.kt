package com.gda.simpleweather.ui.pages.weather_details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.ViewCompat
import androidx.navigation.Navigation
import com.gda.simpleweather.R
import com.gda.simpleweather.databinding.PageWeatherDetailsBinding
import com.gda.simpleweather.interactors.items.ForecastItem
import com.gda.simpleweather.interactors.items.WeatherViewItem
import com.gda.simpleweather.ui.base.BaseMvpFragment
import com.gda.simpleweather.ui.pages.weather_details.WeatherDetailsPresenter
import com.gda.simpleweather.ui.pages.weather_details.WeatherDetailsView
import com.gda.simpleweather.ui.utils.Renderer
import moxy.presenter.InjectPresenter


class WeatherDetailsPage : BaseMvpFragment(), WeatherDetailsView, IWeatherDetails {

    private lateinit var binding: PageWeatherDetailsBinding

    private var forecastSheet: ForecastBottomPage? = null

    @InjectPresenter
    lateinit var presenter: WeatherDetailsPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PageWeatherDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { it ->
            it.getString("name")?.let {
                if (it.isNotBlank())
                    presenter.getWeather(it)
            }
        }

        forecastSheet = ForecastBottomPage()
        forecastSheet?.show(childFragmentManager, "forecast")

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        view?.let {
            Navigation.findNavController(it)
                .navigate(R.id.action_weatherDetailsPage_to_citiesListPage)
        }
    }

    override fun setWeather(info: WeatherViewItem) {
        binding.cityName.text = info.cityName
        binding.currentTemp.text = info.main?.temp?.let {
            "${if (it > 273) "+" else ""}${(it - 273).toInt()} ${
                resources.getString(
                    R.string.deg_symbol
                )
            }C"
        }
        info.weather?.get(0)?.icon?.let {
            Renderer.renderIcon(
                it
            )
        }?.let { binding.weatherIcon.setImageResource(it) }
    }

    override fun setForecast(forecast: List<ForecastItem?>) {
        forecastSheet?.setForecast(forecast)
    }

    override fun setNoData() {

    }

    override fun setProgress(progress: Float) {
        if (ViewCompat.isLaidOut(binding.motionLayout)) {
            binding.motionLayout.progress = progress
        } else {
            binding.motionLayout.post { binding.motionLayout.progress = progress }
        }
    }

}