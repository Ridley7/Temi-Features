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
import kotlinx.coroutines.launch

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


    fun getRecommendation(data: EnvironmentData) {


        avatarManager.setState(AvatarState.THINKING)

        viewModelScope.launch {
            val recommendation = recommendationUseCase.execute(data)

            attentionManager.requestAttention(
                AttentionEvent(
                    source = AttentionSource.SYSTEM,
                    message = recommendation,
                    priority = 5
                )
            )
        }

    }

    //Boton mi entorno
    fun speakEnviroment(data: EnvironmentData){
        val message = """
        La temperatura actual es ${data.temperature} grados.
        El nivel de humedad es ${data.humidity}.
    """.trimIndent()

        attentionManager.requestAttention(
            AttentionEvent(
                source = AttentionSource.SYSTEM,
                message = message,
                priority = 5
            )
        )
    }
}

