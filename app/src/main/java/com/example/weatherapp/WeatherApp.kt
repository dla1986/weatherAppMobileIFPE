package com.example.weatherapp

import android.app.Application
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth

class WeatherApp : Application() {

    companion object {
        const val FLAGS =
            Intent.FLAG_ACTIVITY_SINGLE_TOP or
                    Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    override fun onCreate() {
        super.onCreate()

        val firebaseAuth = FirebaseAuth.getInstance()

        if (firebaseAuth.currentUser != null) {
            goToMain()
        } else {
            goToLogin()
        }
    }

    private fun goToMain() {

        startActivity(
            Intent(
                this,
                MainActivity::class.java
            ).setFlags(FLAGS)
        )
    }

    private fun goToLogin() {

        startActivity(
            Intent(
                this,
                LoginActivity::class.java
            ).setFlags(FLAGS)
        )
    }
}