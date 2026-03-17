import com.franks.agenttemi.domain.model.EnvironmentData

interface OpenIADataSource{
    suspend fun getRecommendation(
        environmentData: EnvironmentData
    ) : AIRecommendation
}