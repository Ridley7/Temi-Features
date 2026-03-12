package com.franks.agenttemi

import EnvironmentRepository
import EnvironmentViewModel
import GetEnvironmentRecommendationUseCase
import ObserveEnvironmentUseCase
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.agenttemi.ui.screens.EnvironmentScreen
import com.franks.agenttemi.data.datasource.MockEnvironmentDataSource
import com.franks.agenttemi.data.repository.EnvironmentRepositoryImplementation
import com.franks.agenttemi.ui.theme.AgentTemiTheme
import com.robotemi.sdk.listeners.OnRobotReadyListener

class MainActivity : ComponentActivity(), OnRobotReadyListener {

    private val viewModel by lazy{

        val dataSource = MockEnvironmentDataSource()
        val repository = EnvironmentRepositoryImplementation(dataSource);

        val observeEnvironmentUseCase = ObserveEnvironmentUseCase(repository);
        val recommendationUseCase = GetEnvironmentRecommendationUseCase()

        EnvironmentViewModel(
            observeEnvironmentUseCase,
            recommendationUseCase
        );
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AgentTemiTheme {

                val environment by viewModel.environment.collectAsState()

                environment?.let { data ->
                    EnvironmentScreen(
                        temperature = data.temperature,
                        methane = data.methane
                    )
                }
            }
        }
    }

    override fun onRobotReady(isReady: Boolean) {
        TODO("Not yet implemented")
    }
}

