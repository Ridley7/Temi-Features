import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.franks.agenttemi.domain.model.enums.AvatarState
import com.franks.agenttemi.domain.model.EnvironmentData
import com.franks.agenttemi.domain.model.enums.SpeechPriority
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class EnvironmentViewModel(
    private val observeEnvironmentUseCase: ObserveEnvironmentUseCase,
    private val recommendationUseCase: GetEnvironmentRecommendationUseCase,
    private val attentionManager: AttentionManager,
    private val avatarManager: AvatarStateManager
) : ViewModel() {

    val avatarState : StateFlow<AvatarState> = avatarManager.state
    val environment = observeEnvironmentUseCase()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            null
        )


    fun getRecommendation(data: EnvironmentData): String {
        val recommendation = recommendationUseCase.execute(data)
        return recommendation
    }

    fun checkEnvironment(data: EnvironmentData){

        if(data.methane > 2000){
            avatarManager.setState(AvatarState.ALERT)
        }else{
            avatarManager.setState(AvatarState.IDLE)
        }

    }

    fun speakEnviroment(data: EnvironmentData){
        val message = """
        La temperatura actual es ${data.temperature} grados.
        El nivel de metano es ${data.methane}.
    """.trimIndent()

        attentionManager.requestAttention(
            AttentionEvent(
                source = AttentionSource.SENSOR,
                message = message,
                priority = 3
            )
        )
    }

    fun testVoice(){
        attentionManager.requestAttention(
            AttentionEvent(
                source = AttentionSource.USER,
                message = "Hola, soy tu agente ambiental",
                priority = 10
            )
        )
    }

}

