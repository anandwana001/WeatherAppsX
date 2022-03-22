package com.anandwana001.weatherappsx.di

import com.anandwana001.weatherappsx.data.api.NetworkService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by anandwana001 on
 * 07, February, 2022
 **/
@Module
@InstallIn(SingletonComponent::class)
class ApplicationTestModule {

  @Provides
  fun provideRetrofit(
    okHttpClient: OkHttpClient,
    moshi: Moshi
  ): NetworkService {
    return Retrofit.Builder()
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .baseUrl("http://127.0.0.1:8080")
      .client(okHttpClient)
      .build()
      .create(NetworkService::class.java)
  }

  @Provides
  fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient
      .Builder()
      .build()
  }

  @Provides
  fun provideMoshi(): Moshi {
    return Moshi.Builder()
      .addLast(KotlinJsonAdapterFactory())
      .build()
  }
}