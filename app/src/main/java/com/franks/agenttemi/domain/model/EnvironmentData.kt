
package com.franks.agenttemi.domain.model

data class EnvironmentData(
    val temperature: Float,
    val humidity: Float,
    val luminosity: Float,
    val sound: Float,
    val flammablesGas : Float,
    val alcohol: Float,
    val carbonMonoxide : Float,
    val smoke: Float,
    val volatileVapors: Float
    )