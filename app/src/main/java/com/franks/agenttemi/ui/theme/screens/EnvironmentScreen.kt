package com.example.agenttemi.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EnvironmentScreen(
    temperature: Float,
    methane: Float,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        // Zona donde irá el avatar (3/4 pantalla aprox)
        Box(
            modifier = Modifier
                .weight(3f)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Avatar",
                fontSize = 28.sp
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Panel derecho con datos ambientales
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Top
        ) {

            Text(
                text = "Mi entorno",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            EnvironmentValue(
                label = "Temperatura",
                value = "$temperature °C"
            )

            Spacer(modifier = Modifier.height(16.dp))

            EnvironmentValue(
                label = "Metano",
                value = "$methane ppm"
            )
        }
    }
}

@Composable
fun EnvironmentValue(
    label: String,
    value: String
) {

    Column {

        Text(
            text = label,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = value,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}