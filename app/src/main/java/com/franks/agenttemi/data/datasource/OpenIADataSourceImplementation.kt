import com.franks.agenttemi.BuildConfig

class OpenIADataSourceImplementation (
    private val openAIService: OpenAIService
): OpenIADataSource{

    override suspend fun getRecommendation(temperature: Float, methane: Float): String {
        val prompt = """
        Eres un asistente ambiental que ayuda a trabajadores a mantener un entorno seguro.

        Temperatura: $temperature grados
        Metano: $methane ppm

        Da una recomendación breve en español (máximo 2 frases).
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