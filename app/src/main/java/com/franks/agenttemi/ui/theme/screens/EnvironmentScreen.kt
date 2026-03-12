package com.example.agenttemi.ui.screens

import AvatarPanel
import ControlButtonsPanel
import EnvironmentInfoPanel
import EnvironmentViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EnvironmentScreen(
    modifier: Modifier = Modifier,
    viewModel: EnvironmentViewModel = koinViewModel()
) {

    val environment by viewModel.environment.collectAsState()

    environment?.let { data ->
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            // Zona donde irá el avatar (3/4 pantalla aprox)
            AvatarPanel(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight()
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Panel derecho con datos ambientales
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                //verticalArrangement = Arrangement.Top
            ) {

                ControlButtonsPanel(
                    environment = data,
                    onRecommendation = {
                        viewModel.getRecommendation(data)
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                EnvironmentInfoPanel(
                    environment = data
                )
            }
        }
    }
}
