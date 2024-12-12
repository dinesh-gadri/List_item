package com.example.cardemo.domain.di

import com.example.cardemo.data.respository.RepositoryImpl
import com.example.cardemo.domain.usecase.GetProductDetailUseCase
import com.example.cardemo.domain.usecase.GetProductListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun productListUseCaseProvider(repositoryImpl: RepositoryImpl) : GetProductListUseCase{
        return GetProductListUseCase(repositoryImpl)
    }

    @Provides
    @Singleton
    fun productDetailUseCaseProvider(repositoryImpl: RepositoryImpl) : GetProductDetailUseCase{
        return GetProductDetailUseCase(repositoryImpl)
    }
}
