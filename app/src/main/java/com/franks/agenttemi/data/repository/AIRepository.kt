interface AIRepository{

    suspend fun getRecommendation(
        temperature: Float,
        methane: Float
    ) : String

}