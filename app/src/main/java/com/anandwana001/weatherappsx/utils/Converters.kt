package com.anandwana001.weatherappsx.utils

import kotlin.math.roundToInt

/**
 * Created by anandwana001 on
 * 06, February, 2022
 **/
object Converters {
  fun kelvinToCelsius(kelvin: Double): Int {
    return (kelvin + Constants.ABSOLUTE_ZERO_IN_CELSIUS).roundToInt()
  }
}
