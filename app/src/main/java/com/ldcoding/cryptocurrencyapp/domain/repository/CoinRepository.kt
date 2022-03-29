package com.ldcoding.cryptocurrencyapp.domain.repository

import com.ldcoding.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.ldcoding.cryptocurrencyapp.data.remote.dto.CoinDto

interface CoinRepository {
    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoinById(coinId: String): CoinDetailDto
}