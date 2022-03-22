package com.anandwana001.weatherappsx

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.anandwana001.weatherappsx.di.ApplicationModule
import com.anandwana001.weatherappsx.ui.HomeActivity
import com.anandwana001.weatherappsx.util.FileReader
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okhttp3.mockwebserver.SocketPolicy
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by anandwana001 on
 * 07, February, 2022
 **/
@UninstallModules(ApplicationModule::class)
@HiltAndroidTest
class HomeActivityTestMockServer {

  @get:Rule
  var hiltRule = HiltAndroidRule(this)

  /**
   * This can be replaced with ActivityScenerio.
   */
  @Rule
  @JvmField
  val activityRule = ActivityTestRule(HomeActivity::class.java, true, false)

  private lateinit var mockWebServer: MockWebServer

  @Before
  fun setup() {
    mockWebServer = MockWebServer()
    mockWebServer.start(8080)
  }

  @After
  fun tearDown() {
    mockWebServer.shutdown()
  }

  private val dispatcher = object : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
      if (request.path!!.contains("weather")) {
        return MockResponse()
          .setResponseCode(200)
          .setBody(FileReader.readStringFromFile("weatherResponse.json"))
      }
      if (request.path!!.contains("forecast")) {
        return MockResponse()
          .setResponseCode(200)
          .setBody(FileReader.readStringFromFile("forecastResponse.json"))
      }
      return MockResponse().setResponseCode(404)
    }
  }

  /**
   * Using Thread class in the espresso test is not a good practice and affect the testing resources
   * alot.
   * The best approach is to use either TestCoroutineDispatchers to provide certain amount of delay
   * or simply idling resources.
   *
   * Keeping the time frame in mind for this assignment, I am directly using the Thread class.
   */
  @Test
  fun test_happyPath_weatherAndForecastIsDisplayed() {
    mockWebServer.dispatcher = dispatcher
    activityRule.launchActivity(null)

    Thread.sleep(3000)
    onView(withId(R.id.todayTemperatureTextView))
      .check(matches(isDisplayed()))
    onView(withId(R.id.locationTextView))
      .check(matches(isDisplayed()))

    Thread.sleep(3000)
    onView(withId(R.id.forecastList))
      .check(matches(isDisplayed()))
  }

  @Test
  fun test_noInternet_failureMessageIsDisplayed() {
    mockWebServer.dispatcher = object : Dispatcher() {
      override fun dispatch(request: RecordedRequest): MockResponse {
        return MockResponse()
          .setSocketPolicy(SocketPolicy.DISCONNECT_AT_START)
      }
    }
    activityRule.launchActivity(null)
    Thread.sleep(3000)
    onView(withId(R.id.failureTextView))
      .check(matches(isDisplayed()))
    onView(withId(R.id.retryButton))
      .check(matches(isDisplayed()))
  }

  @Test
  fun test_responseFailure_failureMessageIsDisplayed() {
    mockWebServer.dispatcher = object : Dispatcher() {
      override fun dispatch(request: RecordedRequest): MockResponse {
        return MockResponse().setResponseCode(404)
      }
    }
    activityRule.launchActivity(null)
    Thread.sleep(3000)
    onView(withId(R.id.todayTemperatureTextView))
      .check(matches(not(isDisplayed())))
    onView(withId(R.id.locationTextView))
      .check(matches(not(isDisplayed())))
    onView(withId(R.id.forecastList))
      .check(matches(not(isDisplayed())))
    onView(withId(R.id.failureTextView))
      .check(matches(isDisplayed()))
    onView(withId(R.id.retryButton))
      .check(matches(isDisplayed()))
  }

  // TODO - test cases
  /**
   * 1. test_responseFailure_clickRetry_weatherAndForecastIsDisplayed()
   */
}
