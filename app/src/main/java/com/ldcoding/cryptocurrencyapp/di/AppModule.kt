package com.ldcoding.cryptocurrencyapp.di

import com.ldcoding.cryptocurrencyapp.common.Constants
import com.ldcoding.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.ldcoding.cryptocurrencyapp.data.repository.CoinRepositoryImpl
import com.ldcoding.cryptocurrencyapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePapikaApi(): CoinPaprikaApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaApi): CoinRepository {
        return CoinRepositoryImpl(api)
    }

}