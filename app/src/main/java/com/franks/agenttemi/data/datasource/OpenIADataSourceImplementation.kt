import android.util.Log
import com.franks.agenttemi.BuildConfig
import com.franks.agenttemi.domain.model.EnvironmentData
import com.google.gson.Gson

class OpenIADataSourceImplementation (
    private val openAIService: OpenAIService
): OpenIADataSource{

    override suspend fun getRecommendation(environmentData: EnvironmentData): AIRecommendation {
        val prompt = """
        Eres un asistente ambiental que ayuda a trabajadores a mantener un entorno seguro.
        Vas a recibir parámetros medidos por sensores.
        Debes analizar datos de sensores y responder SOLO en JSON válido.

        Formato:
        {
          "alert": boolean,
          "severity": "LOW" | "MEDIUM" | "HIGH",
          "message": string
        }
        
        Reglas:
        - Si algún parámetro es alto → alert = true, severity = HIGH
        - Si la temperatura es incómoda → severity MEDIUM
        - Mensaje breve (máx 2 frases)
        - Español

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
        Responde SOLO el JSON.
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

        return try{

            val rawText = response.output.first().content.first().text

            val cleanJson = rawText
                .replace("```json", "")
                .replace("```", "")
                .trim()

            Gson().fromJson<AIRecommendation>(
                cleanJson,
            AIRecommendation::class.java
            )

        }catch (e: Exception){

            Log.d("OPENAI", e.message.toString())

            AIRecommendation(
                alert = false,
                severity = Severity.LOW,
                message = "No se pudo interpretar la recomendación"
            )
        }

        /*
        return response.output
            .first()
            .content
            .first()
            .text
        */

    }


}