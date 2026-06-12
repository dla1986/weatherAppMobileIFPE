package com.example.weatherapp

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts

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
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.weatherapp.ui.CityDialog
import com.example.weatherapp.ui.nav.Route
import androidx.navigation.NavDestination.Companion.hasRoute
import com.google.firebase.auth.FirebaseAuth



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
            val currentRoute = navController.currentBackStackEntryAsState()
            val showButton = currentRoute.value?.destination?.hasRoute(Route.List::class) == true
            val launcher = rememberLauncherForActivityResult(contract =
                ActivityResultContracts.RequestPermission(), onResult = {} )

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


                                        FirebaseAuth.getInstance().signOut()
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

                        if (showButton) {
                            FloatingActionButton(onClick = { showDialog = true }) {
                                Icon(Icons.Default.Add, contentDescription = "Adicionar")
                            }
                        }
                    }

                ) { innerPadding ->

                    Box(modifier = Modifier.padding(innerPadding)) {
                        launcher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)

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