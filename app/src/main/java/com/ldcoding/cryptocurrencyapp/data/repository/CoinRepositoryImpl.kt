package com.ldcoding.cryptocurrencyapp.data.repository

import com.ldcoding.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.ldcoding.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.ldcoding.cryptocurrencyapp.data.remote.dto.CoinDto
import com.ldcoding.cryptocurrencyapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository{
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}