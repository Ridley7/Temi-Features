package com.example.agenttemi.ui.screens

import AvatarPanel
import ControlButtonsPanel
import EnvironmentInfoPanel
import EnvironmentViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import avatarStateModule
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EnvironmentScreen(
    modifier: Modifier = Modifier,
    viewModel: EnvironmentViewModel = koinViewModel()
) {

    val environment by viewModel.environment.collectAsState()
    val avatarState by viewModel.avatarState.collectAsState()

    environment?.let { data ->
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            // Zona donde irá el avatar (3/4 pantalla aprox)
            AvatarPanel(
                state = avatarState,
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
                    },
                    onSpeakEnvironment = {
                        //viewModel.speakEnviroment(data)
                        viewModel.testVoice()
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
