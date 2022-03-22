package com.anandwana001.weatherappsx.di

import com.anandwana001.weatherappsx.BuildConfig
import com.anandwana001.weatherappsx.data.api.NetworkService
import com.anandwana001.weatherappsx.utils.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by anandwana001 on
 * 04, February, 2022
 *
 * Module providing required dependencies. This module is installed at ApplicationComponent which
 * is now known as SingletonComponent in the Dagger hilt.
 **/
@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

  /**
   * The [HttpLoggingInterceptor] helps in logging the request and response data.
   * Attaching the interceptor only in the debug mode.
   */
  @Provides
  @Singleton
  fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder()
      .addInterceptor(loggingInterceptor)
      .build()
  } else OkHttpClient
    .Builder()
    .build()

  /**
   * Moshi’s adapters are ordered by precedence, so we should use addLast() with
   * [KotlinJsonAdapterFactory], and add() with our custom adapters.
   */
  @Provides
  @Singleton
  fun provideMoshi(): Moshi {
    return Moshi.Builder()
      .addLast(KotlinJsonAdapterFactory())
      .build()
  }

  @Provides
  @Singleton
  fun provideRetrofit(
    okHttpClient: OkHttpClient,
    moshi: Moshi
  ): Retrofit =
    Retrofit.Builder()
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .baseUrl(Constants.BASE_URL)
      .client(okHttpClient)
      .build()

  /**
   * [NetworkService], single place where all the API services are defined.
   */
  @Provides
  @Singleton
  fun provideNetworkService(retrofit: Retrofit): NetworkService =
    retrofit.create(NetworkService::class.java)
}
