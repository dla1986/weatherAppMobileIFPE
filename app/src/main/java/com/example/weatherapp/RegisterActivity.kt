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
import com.google.firebase.auth.FirebaseAuth



class RegisterActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            WeatherAPPTheme {

                RegisterPage()

            }
        }
    }
}

@Composable
fun RegisterPage(modifier: Modifier = Modifier) {



    var name by rememberSaveable {
        mutableStateOf("")
    }

    var email by rememberSaveable {
        mutableStateOf("")
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }

    var confirmPassword by rememberSaveable {
        mutableStateOf("")
    }

    val activity = LocalContext.current as Activity



    // Validações

    val isValid =
        name.isNotBlank() &&
                email.isNotBlank() &&
                password.isNotBlank() &&
                confirmPassword.isNotBlank() &&
                password == confirmPassword

    Column(
        modifier = modifier
            .padding(24.dp)
            .fillMaxSize()
    ) {

        Text(
            text = "Registro",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo Nome

        OutlinedTextField(
            value = name,
            label = {
                Text("Nome")
            },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                name = it
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Campo E-mail

        OutlinedTextField(
            value = email,
            label = {
                Text("E-mail")
            },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                email = it
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Campo Senha

        OutlinedTextField(
            value = password,
            label = {
                Text("Senha")
            },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                password = it
            },
            visualTransformation =
                PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Campo Confirmar Senha

        OutlinedTextField(
            value = confirmPassword,
            label = {
                Text("Confirmar Senha")
            },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                confirmPassword = it
            },
            visualTransformation =
                PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row {


            //Botão Registrar


            Button(

                onClick = {

                    FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(
                            email,
                            password
                        )
                        .addOnCompleteListener(activity) { task ->

                            if (task.isSuccessful) {

                                Toast.makeText(
                                    activity,
                                    "Registro OK!",
                                    Toast.LENGTH_LONG
                                ).show()
                                val intent = Intent(activity, MainActivity::class.java)
                                activity.startActivity(intent)
                                activity.finish()


                            } else {

                                Toast.makeText(
                                    activity,
                                    "Registro FALHOU!",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                },

                enabled = isValid

            ) {

                Text("Registrar")

            }

            Spacer(modifier = Modifier.width(12.dp))


            // Limpar formulário


            Button(
                onClick = {

                    name = ""
                    email = ""
                    password = ""
                    confirmPassword = ""

                }
            ) {

                Text("Limpar")

            }
        }
    }
}