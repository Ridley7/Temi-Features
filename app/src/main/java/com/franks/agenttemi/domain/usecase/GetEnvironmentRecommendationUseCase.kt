import android.util.Log
import com.franks.agenttemi.domain.model.EnvironmentData
import retrofit2.HttpException

class GetEnvironmentRecommendationUseCase(
    private val aiRepository: AIRepository
) {

    suspend fun execute(data: EnvironmentData): AIRecommendation {

        return try {
            aiRepository.getRecommendation(
                data
            )
        } catch (e: HttpException){

            Log.e("OpenAI", "Error: ${e.code()} ${e.message()}")

            when(e.code()){
                429 -> return AIRecommendation(
                    alert = false,
                    severity = Severity.LOW,
                    message = "Has hecho demasiadas consultas"
                )
                else -> return AIRecommendation(
                    alert = false,
                    severity = Severity.LOW,
                    message = "Error consultando a la IA"
                )
            }

        }

    }

}