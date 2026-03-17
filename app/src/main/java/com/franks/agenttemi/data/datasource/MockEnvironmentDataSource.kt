package com.franks.agenttemi.data.datasource

import EnvironmentDataSource
import com.franks.agenttemi.domain.model.EnvironmentData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockEnvironmentDataSource : EnvironmentDataSource{
    override fun observeEnvironment(): Flow<EnvironmentData> = flow {

        while(true){
             val data = EnvironmentData(
                 temperature = (18..25).random().toFloat(),
                 humidity = (400..900).random().toFloat(),
                 luminosity = (400..900).random().toFloat(),
                 sound = (400..900).random().toFloat(),
                 flammablesGas = (400..900).random().toFloat(),
                 alcohol = (400..900).random().toFloat(),
                 carbonMonoxide = (400..900).random().toFloat(),
                 smoke = (400..900).random().toFloat(),
                 volatileVapors = (400..900).random().toFloat(),
             )

            emit(data)

            delay(3000)
        }
    }
}
