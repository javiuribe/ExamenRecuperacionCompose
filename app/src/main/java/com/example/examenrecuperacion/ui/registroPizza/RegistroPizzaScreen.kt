package com.example.examenrecuperacion.ui.registroPizza

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.examenrecuperacion.model.PizzaDto
import com.example.examenrecuperacion.model.PizzaSize

@Composable
fun RegistroPizzaScreen(viewModel: RegistroPizzaViewModel) {
    val pizza by viewModel.pizza.observeAsState(initial = PizzaDto())
    val listaPizzas by viewModel.listaPizzas.observeAsState(initial = mutableListOf())
    val precioTexto = if (pizza.precio == 0.0) "" else pizza.precio.toString()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Título
        Text(
            text = "Añadir pizza",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        // Campo Nombre
        PizzaTextField(
            label = "Nombre",

            value = pizza.nombre,
            onValueChange = { newName ->
                viewModel.onChangePizza(pizza.copy(nombre = newName))
            }
        )
        // Campo Precio
        PizzaTextField(
            label = "Precio",
            value = precioTexto,
            keyboard = KeyboardType.Number,
            onValueChange = { newPrecio ->
                val precio = newPrecio.toDoubleOrNull() ?: 0.0
                viewModel.onChangePizza(pizza.copy(precio = precio))
            }
        )
        PizzaDropdown(seletecedSize = pizza.size) { newSize ->
            viewModel.onChangePizza(pizza.copy(size = newSize))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { viewModel.addPizza() }) {
                Text("Añadir pizza")
            }
        }
        listaPizzas?.forEach { pizza ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = pizza.nombre, modifier = Modifier.weight(1f))
                Text(text = "${pizza.precio} €", modifier = Modifier.weight(1f))
                Text(text = pizza.size.name, modifier = Modifier.weight(1f), textAlign = TextAlign.End)
            }
        }

    }
}

@Composable
fun PizzaTextField(label: String, value: String, keyboard: KeyboardType= KeyboardType.Text, onValueChange: (String) -> Unit) {
    OutlinedTextField(

        value = value,
        keyboardOptions = KeyboardOptions(keyboardType = keyboard),
        onValueChange = onValueChange,
        label = { Text(text = label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}

@Composable
fun PizzaDropdown(
    seletecedSize: PizzaSize,
    onSizeChange: (PizzaSize) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Tamaño:",
            modifier = Modifier.padding(end = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )

        Box {
            TextButton(
                onClick = { expanded = true }
            ) {
                Text(text = seletecedSize.name.replace("_", " "))
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                PizzaSize.entries.forEach { pizza ->
                    DropdownMenuItem(
                        text = { Text(pizza.name.replace("_", " ")) },
                        onClick = {
                            onSizeChange(pizza)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}