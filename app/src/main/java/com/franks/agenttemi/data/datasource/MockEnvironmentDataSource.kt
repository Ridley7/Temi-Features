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
                 methane = (400..900).random().toFloat(),
             )

            emit(data)

            delay(3000)
        }
    }
}
