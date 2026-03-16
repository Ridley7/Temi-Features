import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OpenAIService{

    @POST("v1/responses")
    suspend fun getRecommendation(
        @Header("Authorization") auth:String,
        @Body request: OpenAIRequest
    ): OpenAIResponse

}