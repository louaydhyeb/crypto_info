package com.ldcoding.cryptocurrencyapp.domain.use_case.get_coins

import com.ldcoding.cryptocurrencyapp.common.Resource
import com.ldcoding.cryptocurrencyapp.data.remote.dto.toCoin
import com.ldcoding.cryptocurrencyapp.domain.model.Coin
import com.ldcoding.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success<List<Coin>>(coins))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An enexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "No Internet conection coudn't reach server"))
        }
    }
}