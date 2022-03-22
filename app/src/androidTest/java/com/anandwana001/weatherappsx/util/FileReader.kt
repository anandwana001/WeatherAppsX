package com.anandwana001.weatherappsx.util

import androidx.test.platform.app.InstrumentationRegistry
import dagger.hilt.android.testing.HiltTestApplication
import java.io.IOException
import java.io.InputStreamReader

/**
 * Created by anandwana001 on
 * 07, February, 2022
 *
 * Help class to read the file.
 **/
object FileReader {

  fun readStringFromFile(fileName: String): String {
    try {
      val inputStream =
        (InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as HiltTestApplication).assets.open(
          fileName
        )
      val builder = StringBuilder()
      val reader = InputStreamReader(inputStream, "UTF-8")
      reader.readLines().forEach {
        builder.append(it)
      }
      return builder.toString()
    } catch (e: IOException) {
      throw e
    }
  }
}