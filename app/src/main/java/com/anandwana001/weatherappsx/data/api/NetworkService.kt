package com.anandwana001.weatherappsx.data.api

import com.anandwana001.weatherappsx.BuildConfig.WEATHER_API_ID
import com.anandwana001.weatherappsx.data.Endpoints
import com.anandwana001.weatherappsx.data.model.CurrentTemperatureResponse
import com.anandwana001.weatherappsx.data.model.ForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by anandwana001 on
 * 04, February, 2022
 **/
interface NetworkService {

  @GET(Endpoints.WEATHER)
  suspend fun getCurrentTemperature(
    @Query("q") city_name: String,
    @Query("APPID") api_key: String = WEATHER_API_ID
  ): Response<CurrentTemperatureResponse>

  @GET(Endpoints.FORECAST)
  suspend fun getForecast(
    @Query("q") city_name: String,
    @Query("APPID") api_key: String = WEATHER_API_ID
  ): Response<ForecastResponse>
}
