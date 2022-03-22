package com.anandwana001.weatherappsx

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

/**
 * Created by anandwana001 on
 * 07, February, 2022
 **/
class CustomTestRunner : AndroidJUnitRunner() {

  override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
    return super.newApplication(cl, HiltTestApplication::class.java.name, context)
  }
}