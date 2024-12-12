package com.example.cardemo.domain.usecase

import com.example.cardemo.core.common.UiState
import com.example.cardemo.data.respository.RepositoryImpl
import com.example.cardemo.domain.model.ProductDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(private val repositoryImpl : RepositoryImpl)  {

    operator fun invoke(id : String) : Flow<UiState<ProductDetail>> = flow {
        emit(UiState.Loading())
        try {
            emit(UiState.Success(data = repositoryImpl.getProductDetail(id)))
        }catch (e : Exception){
            emit(UiState.Error(message = e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

}
