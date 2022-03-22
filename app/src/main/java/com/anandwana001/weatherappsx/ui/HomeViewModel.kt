package com.anandwana001.weatherappsx.ui

import android.text.format.DateFormat
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anandwana001.weatherappsx.data.repository.NetworkRepository
import com.anandwana001.weatherappsx.utils.Converters.kelvinToCelsius
import com.anandwana001.weatherappsx.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by anandwana001 on
 * 06, February, 2022
 **/
@HiltViewModel
class HomeViewModel @Inject constructor(
  private val homeRepository: NetworkRepository,
  private val networkHelper: NetworkHelper
) : ViewModel() {

  private val _currentTemp = MutableLiveData<Int>()
  val currentTemp: LiveData<Int>
    get() = _currentTemp

  private val _cityName = MutableLiveData<String>()
  val cityName: LiveData<String>
    get() = _cityName

  private val _forecastList = MutableLiveData<List<ForecastItemViewModel>>()
  val forecastList: LiveData<List<ForecastItemViewModel>>
    get() = _forecastList

  private val _isLoaded = MutableLiveData<Boolean>(false)
  val isLoaded: LiveData<Boolean>
    get() = _isLoaded

  private val _isFailure = MutableLiveData<Boolean>(false)
  val isFailure: LiveData<Boolean>
    get() = _isFailure

  private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
    _isFailure.value = true
  }

  init {
    fetchCurrentTempAndForecast()
  }

  /**
   * Exception handling of coroutines can be done with more elegant way. For this assignment time
   * frame, doing in the brute force approach.
   */
  private fun fetchCurrentTempAndForecast() {
    viewModelScope.launch(exceptionHandler) {
      if (networkHelper.isNetworkConnected()) {
        fetchTodayWeather(this)
        fetchForecast(this)
        _isLoaded.value = true
      } else {
        _isFailure.value = true
      }
    }
  }

  // TODO - improve the below brute force approach
  suspend private fun fetchForecast(coroutineScope: CoroutineScope) {
    homeRepository.getForecast("Bengaluru").let { response ->
      if (response.isSuccessful) {
        response.body()?.let { data ->
          val forecastTempList = ArrayList<ForecastItemViewModel>()
          val today = computeDayString(data.forecastList[0].dateAndTime)
          var tomorrow = ""
          for (forecast in data.forecastList) {
            if (computeDayString(forecast.dateAndTime) != today) {
              tomorrow = computeDayString(forecast.dateAndTime)
              break
            }
          }
          var countOfDay = 0
          var sumOfAllTemp = 0.0
          data.forecastList.forEach { forecast ->
            if (computeDayString(forecast.dateAndTime) != today) {
              if (computeDayString(forecast.dateAndTime) == tomorrow) {
                sumOfAllTemp += forecast.currentTemperatureMain.temperature
                countOfDay++
              } else {
                val forecastItem =
                  ForecastItemViewModel(
                    sumOfAllTemp / countOfDay,
                    tomorrow
                  )
                tomorrow = computeDayString(forecast.dateAndTime)
                sumOfAllTemp = 0.0
                countOfDay = 0
                forecastTempList.add(forecastItem)
              }
            }
          }
          _forecastList.value = forecastTempList
        }
      } else {
        coroutineScope.cancel()
        _isFailure.value = true
      }
    }
  }

  suspend private fun fetchTodayWeather(coroutineScope: CoroutineScope) {
    homeRepository.getCurrentTemperature("Bengaluru").let { response ->
      if (response.isSuccessful) {
        response.body()?.let { data ->
          val temperatureInCelsius =
            kelvinToCelsius(data.currentTemperatureResponse.temperature)
          _currentTemp.value = temperatureInCelsius
          _cityName.value = data.cityName
        }
      } else {
        _isFailure.value = true
        coroutineScope.cancel()
      }
    }
  }

  fun retryDataFetch(@Suppress("UNUSED_PARAMETER") v: View) {
    _isLoaded.value = false
    _isFailure.value = false
    fetchCurrentTempAndForecast()
  }

  private fun computeDayString(dateAndTime: String): String {
    val processedDate = dateAndTime.substringBefore(" ")
    val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(processedDate)
    return DateFormat.format("EEEE", date).toString()
  }
}