package com.example.weatherapp

import android.Manifest
import android.content.pm.PackageManager

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.getDrawable
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.scale

import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng

import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.example.weatherapp.model.Weather

@Composable
fun MapPage(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {

    val context = LocalContext.current

    val hasLocationPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val recife = remember {
        MarkerState(
            position = LatLng(-8.05, -34.90)
        )
    }

    val caruaru = remember {
        MarkerState(
            position = LatLng(-8.27, -35.98)
        )
    }

    val joaopessoa = remember {
        MarkerState(
            position = LatLng(-7.12, -34.84)
        )
    }

    val camPosState = rememberCameraPositionState()

    GoogleMap(
        modifier = modifier,
        cameraPositionState = camPosState,
        properties = MapProperties(
            isMyLocationEnabled = hasLocationPermission
        ),
        uiSettings = MapUiSettings(
            myLocationButtonEnabled = true
        ),
        onMapClick = { location ->
            viewModel.addCity(location)
        }
    ) {

        // Recife
        Marker(
            state = recife,
            title = "Recife",
            snippet = "Marcador em Recife",
            icon = BitmapDescriptorFactory.defaultMarker(
                BitmapDescriptorFactory.HUE_BLUE
            )
        )

        // Caruaru
        Marker(
            state = caruaru,
            title = "Caruaru",
            snippet = "Marcador em Caruaru",
            icon = BitmapDescriptorFactory.defaultMarker(
                BitmapDescriptorFactory.HUE_GREEN
            )
        )

        // João Pessoa
        Marker(
            state = joaopessoa,
            title = "João Pessoa",
            snippet = "Marcador em João Pessoa",
            icon = BitmapDescriptorFactory.defaultMarker(
                BitmapDescriptorFactory.HUE_ORANGE
            )
        )


        viewModel.cities.forEach { city ->
            if (city.location != null) {

                val weather = viewModel.weather(city.name)
                val image = weather.bitmap ?:
                getDrawable(context, R.drawable.loading)!!.toBitmap()

                val marker = BitmapDescriptorFactory
                    .fromBitmap(image.scale(120,120))

                val desc = if (weather == Weather.LOADING) "Carregando clima..." else weather.desc

                Marker(
                    state = MarkerState(position = city.location),
                    icon = marker,
                    title = city.name,
                    snippet = desc
                )
            }
        }
    }
}