class AIRepositoryImplementation (
    private val remoteDataSource : OpenIADataSource
):AIRepository {
    override suspend fun getRecommendation(temperature: Float, methane: Float): String {
        return remoteDataSource.getRecommendation(temperature, methane)
    }
}