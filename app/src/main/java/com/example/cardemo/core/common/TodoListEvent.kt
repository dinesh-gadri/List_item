package com.example.cardemo.core.common

import com.example.cardemo.domain.model.ProductItem

sealed class TodoListEvent {
    data class OnTodoClick(val todo: ProductItem): TodoListEvent()
}