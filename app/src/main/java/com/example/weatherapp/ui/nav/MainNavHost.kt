package com.example.weatherapp.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapp.HomePage
import com.example.weatherapp.ListPage
import com.example.weatherapp.MapPage
import com.example.weatherapp.MainViewModel

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel // Passo 5: Recebe o ViewModel como parâmetro
) {
    NavHost(
        navController = navController,
        startDestination = Route.Home,
        modifier = modifier
    ) {
        composable<Route.Home> {
            // Passo 3: Repassa o viewModel para a HomePage
            HomePage(modifier = modifier, viewModel = viewModel)
        }

        composable<Route.List> {
            // Passo 3: Repassa o viewModel para a ListPage
            ListPage(modifier = modifier, viewModel = viewModel)
        }

        composable<Route.Map> {
            // Passo 3: Repassa o viewModel para a MapPage
            MapPage(modifier = modifier, viewModel = viewModel)
        }
    }
}