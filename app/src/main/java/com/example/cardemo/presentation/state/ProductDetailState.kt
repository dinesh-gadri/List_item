package com.example.cardemo.presentation.state

import com.example.cardemo.domain.model.ProductDetail

data class ProductDetailState(val isLoading : Boolean = false,
                              val data : ProductDetail? = null,
                              var error : String ="")
