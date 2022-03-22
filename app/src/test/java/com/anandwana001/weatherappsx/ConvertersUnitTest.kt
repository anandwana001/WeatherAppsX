package com.anandwana001.weatherappsx

import com.anandwana001.weatherappsx.utils.Converters
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Created by anandwana001 on
 * 06, February, 2022
 **/
@RunWith(RobolectricTestRunner::class)
class ConvertersUnitTest {

  @Test
  fun test_kelvinToCelsiusConversion_isCorrect() {
    val kelvinTemperature = 298.6
    val celsiusTemperature = 25
    val tempInCelsius = Converters.kelvinToCelsius(kelvinTemperature)

    assertThat(tempInCelsius).isEqualTo(celsiusTemperature)
  }
}