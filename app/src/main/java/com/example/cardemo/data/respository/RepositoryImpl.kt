package com.example.cardemo.data.respository

import com.example.cardemo.core.common.toProductDetail
import com.example.cardemo.core.common.toProductList
import com.example.cardemo.data.netwotk.ApiService
import com.example.cardemo.domain.model.ProductDetail
import com.example.cardemo.domain.model.ProductItem
import com.example.cardemo.domain.repository.Repository
import javax.inject.Inject


class RepositoryImpl @Inject constructor(private val apiService: ApiService) : Repository {

    override suspend fun getProductList(): List<ProductItem> {
       return apiService.getAllProductListAPI().map { it.toProductList() }
    }

    override suspend fun getProductDetail(id: String): ProductDetail {
        return apiService.getProductDetailsAPI(id).toProductDetail()
    }
}
