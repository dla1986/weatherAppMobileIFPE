package com.example.weatherapp

import android.app.Activity
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.weatherapp.ui.theme.WeatherAPPTheme



class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            WeatherAPPTheme {

                HomePage()

            }
        }
    }
}

@Composable
fun HomePage(modifier: Modifier = Modifier) {

    val activity = LocalContext.current as Activity

    Column(
        modifier = modifier
            .padding(24.dp)
            .fillMaxSize()
    ) {

        Text(
            text = "Bem-vindo ao sistema!",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(20.dp))


        // Botão sair


        Button(
            onClick = {

                activity.finish()

            }
        ) {

            Text("Sair")

        }
    }
}