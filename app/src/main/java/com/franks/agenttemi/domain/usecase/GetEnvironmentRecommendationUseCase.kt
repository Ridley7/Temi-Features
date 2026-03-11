import com.franks.agenttemi.domain.model.EnvironmentData

class GetEnvironmentRecommendationUseCase {

    fun execute(data: EnvironmentData): String {

        return when {

            data.temperature < 18 ->
                "La temperatura es baja, se recomienda encender la calefacción"

            data.temperature > 26 ->
                "La temperatura es alta, se recomienda ventilar"

            data.methane > 800 ->
                "Los niveles de metano son altos, se recomienda ventilar"

            else ->
                "Las condiciones ambientales son adecuadas"
        }

    }

}