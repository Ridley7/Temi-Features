package com.franks.agenttemi.data.repository

import EnvironmentDataSource
import EnvironmentRepository
import com.franks.agenttemi.domain.model.EnvironmentData
import kotlinx.coroutines.flow.Flow

class EnvironmentRepositoryImplementation(
    private val dataSource: EnvironmentDataSource
) : EnvironmentRepository {

    override fun observeEnvironment(): Flow<EnvironmentData> {
        return dataSource.observeEnvironment()
    }
}
