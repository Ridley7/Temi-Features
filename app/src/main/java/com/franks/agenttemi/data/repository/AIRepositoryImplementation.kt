import com.franks.agenttemi.domain.model.EnvironmentData

class AIRepositoryImplementation (
    private val remoteDataSource : OpenIADataSource
):AIRepository {
    override suspend fun getRecommendation(environmentData: EnvironmentData): AIRecommendation {
        return remoteDataSource.getRecommendation(environmentData)
    }
}