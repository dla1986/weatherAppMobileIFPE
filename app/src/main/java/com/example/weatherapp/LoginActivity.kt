package com.example.weatherapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.weatherapp.ui.theme.WeatherAPPTheme


class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            WeatherAPPTheme {

                LoginPage()

            }
        }
    }
}

@Composable
fun LoginPage(modifier: Modifier = Modifier) {



    var email by rememberSaveable {
        mutableStateOf("")
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }

    val activity = LocalContext.current as Activity

    //validação para o botão aparecer quando tiver preenchido

    val isValid =
        email.isNotBlank() &&
                password.isNotBlank()

    Column(
        modifier = modifier
            .padding(24.dp)
            .fillMaxSize()
    ) {

        Text(
            text = "Bem-vindo!",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

       //Campo de email

        OutlinedTextField(
            value = email,
            label = {
                Text("Digite seu e-mail")
            },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                email = it
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        //campo senha

        OutlinedTextField(
            value = password,
            label = {
                Text("Digite sua senha")
            },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                password = it
            },
            visualTransformation =
                PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row {

            //Login com toast

            Button(
                onClick = {

                    Toast.makeText(
                        activity,
                        "Login realizado!",
                        Toast.LENGTH_LONG
                    ).show()

                    //mainactiviry

                    activity.startActivity(
                        Intent(
                            activity,
                            MainActivity::class.java
                        )
                    )

                },



                enabled = isValid

            ) {

                Text("Login")

            }

            Spacer(modifier = Modifier.width(12.dp))

          //Chamar Tela de registro

            Button(
                onClick = {

                    activity.startActivity(
                        Intent(
                            activity,
                            RegisterActivity::class.java
                        )
                    )

                }
            ) {

                Text("Registrar")

            }
        }
    }
}