import com.franks.agenttemi.domain.model.EnvironmentData

interface AIRepository{

    suspend fun getRecommendation(
        environmentData: EnvironmentData
    ) : String

}