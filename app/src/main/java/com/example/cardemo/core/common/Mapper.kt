package com.example.cardemo.core.common

import com.example.cardemo.data.model.ProductListDTO
import com.example.cardemo.domain.model.ProductDetail
import com.example.cardemo.domain.model.ProductItem


fun ProductListDTO.toProductList(): ProductItem {
    return ProductItem(
        id = this.id,
        image = this.image,
        title = this.title,
        description = this.description,
        price = this.price,
    )
}

fun ProductListDTO.toProductDetail(): ProductDetail {
    return ProductDetail(
        category = this.category,
        description = this.description,
        id = this.id,
        image = this.image,
        price = this.price,
        title = this.title
    )
}



