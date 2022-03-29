package com.ldcoding.cryptocurrencyapp.presentation.coin_detail

import com.ldcoding.cryptocurrencyapp.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading : Boolean = false,
    val coin : CoinDetail? = null,
    val error: String = ""
)
