package com.franks.agenttemi

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
import com.example.agenttemi.ui.screens.EnvironmentScreen
import com.franks.agenttemi.ui.theme.AgentTemiTheme
import com.robotemi.sdk.listeners.OnRobotReadyListener

class MainActivity : ComponentActivity(), OnRobotReadyListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AgentTemiTheme {
                EnvironmentScreen(
                    temperature = 21.5f,
                    methane = 450f
                )
            }
        }
    }

    override fun onRobotReady(isReady: Boolean) {
        TODO("Not yet implemented")
    }
}

