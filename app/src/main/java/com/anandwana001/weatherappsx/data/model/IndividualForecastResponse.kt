package com.anandwana001.weatherappsx.data.model

import com.squareup.moshi.Json

/**
 * Created by anandwana001 on
 * 05, February, 2022
 **/
data class IndividualForecastResponse(
  @Json(name = "main")
  val currentTemperatureMain: CurrentTemperatureResponseMain,
  @Json(name = "dt_txt")
  val dateAndTime: String
)
