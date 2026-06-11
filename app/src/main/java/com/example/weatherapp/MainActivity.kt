package com.example.weatherapp

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Add

import androidx.compose.material3.*

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier

import androidx.navigation.compose.rememberNavController

import com.example.weatherapp.ui.nav.BottomNavBar
import com.example.weatherapp.ui.nav.BottomNavItem
import com.example.weatherapp.ui.nav.MainNavHost

import com.example.weatherapp.ui.theme.WeatherAPPTheme
import androidx.compose.runtime.*
import com.example.weatherapp.ui.CityDialog

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            val mainViewModel: MainViewModel = viewModel() //passo 6 atividade 3
            val navController =
                rememberNavController()
            var showDialog by remember {
                mutableStateOf(false)
            }

            WeatherAPPTheme {

                if (showDialog) {

                    CityDialog(

                        onDismiss = {
                            showDialog = false
                        },

                        onConfirm = { city ->

                            if (city.isNotBlank()) {
                                mainViewModel.add(city)
                            }

                            showDialog = false
                        }
                    )
                }

                Scaffold(

                    topBar = {

                        TopAppBar(

                            title = {

                                Text("Bem-vindo!")

                            },

                            actions = {

                                IconButton(
                                    onClick = {

                                        finish()

                                    }
                                ) {

                                    Icon(
                                        imageVector =
                                            Icons.AutoMirrored.Filled.ExitToApp,

                                        contentDescription = "Sair"
                                    )
                                }
                            }
                        )
                    },

                    bottomBar = {

                        val items = listOf(

                            BottomNavItem.HomeButton,
                            BottomNavItem.ListButton,
                            BottomNavItem.MapButton

                        )

                        BottomNavBar(

                            navController = navController,
                            items = items

                        )
                    },

                    floatingActionButton = {

                        FloatingActionButton(
                            onClick = {
                                showDialog = true
                            }

                        ) {

                            Icon(
                                Icons.Default.Add,
                                contentDescription = "Adicionar"
                            )
                        }
                    }

                ) { innerPadding ->

                    Box(

                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)

                    ) {

                        MainNavHost(
                            navController = navController,
                            viewModel = mainViewModel
                        )
                    }
                }
            }
        }
    }
}