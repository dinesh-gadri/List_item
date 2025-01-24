package com.example.cardemo.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardemo.core.common.AddEditTodoEvent
import com.example.cardemo.core.common.UiEvent
import com.example.cardemo.core.common.UiState
import com.example.cardemo.domain.usecase.GetProductDetailUseCase
import com.example.cardemo.presentation.state.ProductDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailVewModel @Inject constructor(
    private val productDetailUseCase: GetProductDetailUseCase
) : ViewModel() {

    private val _productDetail = mutableStateOf(ProductDetailState())
    val productDetail: State<ProductDetailState> get() = _productDetail

    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun getProductDetailAPi(id: String) {
        productDetailUseCase.invoke(id).onEach {
            when (it) {
                is UiState.Loading -> {
                    _productDetail.value = ProductDetailState(isLoading = true)
                }

                is UiState.Success -> {
                    _productDetail.value = ProductDetailState(data = it.data)
                }

                is UiState.Error -> {
                    _productDetail.value = ProductDetailState(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }



    fun onEvent(event: AddEditTodoEvent) {
        when(event) {
            is AddEditTodoEvent.OnDescriptionChange -> TODO()
            is AddEditTodoEvent.OnTitleChange -> TODO()
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}
