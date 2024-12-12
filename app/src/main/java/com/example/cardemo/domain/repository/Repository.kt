package com.example.cardemo.domain.repository

import com.example.cardemo.domain.model.ProductDetail
import com.example.cardemo.domain.model.ProductItem

interface Repository {

    suspend fun getProductList() : List<ProductItem>

    suspend fun getProductDetail(id : String) : ProductDetail

}