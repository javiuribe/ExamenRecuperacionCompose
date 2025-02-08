package com.example.examenrecuperacion.model
enum class PizzaSize {
    FAMILIAR, MEDIANA, PEQUENYA;
}

data class PizzaDto(
    val id: Int? = null,
    val nombre: String = "",
    val precio: Double = 0.0,
    val size: PizzaSize = PizzaSize.PEQUENYA
)
