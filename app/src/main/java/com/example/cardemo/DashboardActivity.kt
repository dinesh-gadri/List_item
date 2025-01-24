package com.example.cardemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cardemo.core.common.Routes
import com.example.cardemo.presentation.screens.AddEditTodoScreen
import com.example.cardemo.presentation.screens.CarListingScreen
import com.example.cardemo.presentation.viewmodel.ProductDetailVewModel
import com.example.cardemo.presentation.viewmodel.ProductListVewModel
import com.example.cardemo.ui.theme.ComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : ComponentActivity() {

    private val productListVewModel: ProductListVewModel by viewModels()
    private val productDetailVewModel: ProductDetailVewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Routes.PRODUCT_LIST
                    ) {
                        composable(Routes.PRODUCT_LIST) {
                            CarListingScreen(
                                onNavigate = {
                                    navController.navigate(it.route)
                                },
                                viewModel = productListVewModel
                            )

                        }
                        composable(
                            route = Routes.PRODUCT_DETAILS + "?todoId={todoId}",
                            arguments = listOf(
                                navArgument(name = "todoId") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            AddEditTodoScreen(
                                onPopBackStack = {
                                    navController.popBackStack()
                                },
                                viewModel = productDetailVewModel
                            )
                        }
                    }
                }
            }
        }
    }
}
