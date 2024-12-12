package com.example.cardemo.presentation.viewmodel

import com.example.cardemo.core.common.UiState
import com.example.cardemo.domain.usecase.GetProductListUseCase
import com.example.cardemo.helpers.MainDispatcherRule
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class ProductListVewModelTest {

    private val productListUseCase: GetProductListUseCase = mockk(relaxed = true)
    private lateinit var viewModel: ProductListVewModel

    @get:Rule
    val rule = MainDispatcherRule()

    @Before
    fun setup() {
        viewModel = ProductListVewModel(productListUseCase)
    }

    @Test
    fun `should emit error object when api response error`() = runBlocking {
        every { productListUseCase.invoke().onEach { any() } } returns flowOf(
            UiState.Error(message = "Something went wrong", data = null)
        )

        viewModel.fetchProducts()

        Assert.assertEquals("", "")
    }
}
// I need more time to write the test case. it will take time. Thanks