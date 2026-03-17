import com.franks.agenttemi.BuildConfig
import com.franks.agenttemi.domain.model.EnvironmentData

class OpenIADataSourceImplementation (
    private val openAIService: OpenAIService
): OpenIADataSource{

    override suspend fun getRecommendation(environmentData: EnvironmentData): String {
        val prompt = """
        Eres un asistente ambiental que ayuda a trabajadores a mantener un entorno seguro.
        Vas a recibir parámetros medidos por sensores.

        Temperatura: ${environmentData.temperature} grados
        Humedad: ${environmentData.humidity} %
        Luminosidad: ${environmentData.luminosity} lx
        Sonido: ${environmentData.sound} dB
        Gases inflamables: ${environmentData.flammablesGas} ppm
        Alcohol: ${environmentData.alcohol} ppm
        Monóxido de carbono: ${environmentData.carbonMonoxide} ppm
        Humo: ${environmentData.smoke} ppm
        Vapores volatiles: ${environmentData.volatileVapors}

        Da una recomendación breve en español (máximo 2 frases).
        Si todos los valores estan dentro de unos valores que no son perjudiciales para el 
        ser humano, con que digas algo como que todo esta dentro de la normalidad es suficiente.
        Solo recomendaciones para valores que esten por fuera de lo normal.
        """.trimIndent()

        val request = OpenAIRequest(
            model = "gpt-4.1-mini",
            input = prompt
        )

        //se supone que aqui hay una espera

        val response = openAIService.getRecommendation(
            "Bearer ${BuildConfig.API_KEY}",
            request
        )

        return response.output
            .first()
            .content
            .first()
            .text

    }


}