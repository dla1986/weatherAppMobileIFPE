package com.example.weatherapp

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.City
import com.google.android.gms.maps.model.LatLng

class MainViewModel : ViewModel() {
    // Lista  interna
    private val _cities = getCities().toMutableStateList()

    // Lista exposta para a UI (apenas leitura)
    val cities: List<City>
        get() = _cities.toList()

    // Função para remover uma cidade
    fun remove(city: City) {
        _cities.remove(city)
    }

    // Função para adicionar uma cidade
    fun add(name: String, location: LatLng? = null) {
        _cities.add(City(name = name, location = location))
    }
}

// Passo 2: A função getCities()
private fun getCities() = List(20) { i ->
    City(name = "Cidade $i", weather = "Carregando clima...")
}