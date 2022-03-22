package com.anandwana001.weatherappsx.ui

import androidx.lifecycle.ViewModel
import com.anandwana001.weatherappsx.utils.Converters

/**
 * Created by anandwana001 on
 * 06, February, 2022
 **/
class ForecastItemViewModel(
  private val averageTemp: Double,
  val day: String
) : ViewModel() {

  val temperature: Int by lazy {
    setTemperature()
  }

  private fun setTemperature(): Int {
    return Converters.kelvinToCelsius(averageTemp)
  }
}