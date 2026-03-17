import android.util.Log
import com.franks.agenttemi.domain.model.EnvironmentData
import retrofit2.HttpException

class GetEnvironmentRecommendationUseCase(
    private val aiRepository: AIRepository
) {

    suspend fun execute(data: EnvironmentData): String {

        return try {
            aiRepository.getRecommendation(
                data.temperature,
                data.humidity
            )
        } catch (e: HttpException){

            Log.e("OpenAI", "Error: ${e.code()} ${e.message()}")

            when(e.code()){
                429 -> return "Has hecho demasiadas consultas"
                else -> return "Error consultando a la IA"
            }

        }

    }

}