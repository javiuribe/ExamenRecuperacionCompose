package com.example.examenrecuperacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.examenrecuperacion.ui.registroPizza.RegistroPizzaScreen
import com.example.examenrecuperacion.ui.registroPizza.RegistroPizzaViewModel
import com.example.examenrecuperacion.ui.theme.ExamenRecuperacionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExamenRecuperacionTheme {
                RegistroPizzaScreen(RegistroPizzaViewModel())
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExamenRecuperacionTheme {
        Greeting("Android")
    }
}