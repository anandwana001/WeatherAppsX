package com.anandwana001.weatherappsx.data.model

import com.squareup.moshi.Json

/**
 * Created by anandwana001 on
 * 05, February, 2022
 **/
data class ForecastResponse(
  @Json(name = "list")
  val forecastList: List<IndividualForecastResponse>
)
