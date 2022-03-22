package com.anandwana001.weatherappsx.data.repository

import com.anandwana001.weatherappsx.data.api.NetworkService
import com.anandwana001.weatherappsx.data.model.CurrentTemperatureResponse
import com.anandwana001.weatherappsx.data.model.ForecastResponse
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by anandwana001 on
 * 04, February, 2022
 *
 * This repository helps in interacting with the network service API from the view model.
 **/
class NetworkRepository @Inject constructor(private val networkService: NetworkService) {

  suspend fun getCurrentTemperature(cityName: String): Response<CurrentTemperatureResponse> {
    return networkService.getCurrentTemperature(cityName)
  }

  suspend fun getForecast(cityName: String): Response<ForecastResponse> {
    return networkService.getForecast(cityName)
  }
}
