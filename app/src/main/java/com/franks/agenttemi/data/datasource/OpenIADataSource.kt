interface OpenIADataSource{
    suspend fun getRecommendation(
        temperature: Float,
        methane: Float
    ) : String
}