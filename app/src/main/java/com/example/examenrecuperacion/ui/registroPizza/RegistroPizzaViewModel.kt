package com.example.examenrecuperacion.ui.registroPizza

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.examenrecuperacion.model.PizzaDto

class RegistroPizzaViewModel : ViewModel() {
    val pizza = MutableLiveData<PizzaDto>()
    val listaPizzas = MutableLiveData<MutableList<PizzaDto>>(mutableListOf())

    fun onChangePizza(pizzaChanged: PizzaDto) {
        Log.d("Tamanyo", "Cambio en pizza:$pizzaChanged")
        pizza.value = pizzaChanged
    }

    fun addPizza() {
        val currentList = listaPizzas.value ?: mutableListOf()
        val newList = currentList.toMutableList() // Creamos una nueva lista para forzar la actualización
        pizza.value?.let {
            newList.add(it.copy())
            listaPizzas.value = newList
        }
        Log.d("Agregada", listaPizzas.value.toString())
    }


}