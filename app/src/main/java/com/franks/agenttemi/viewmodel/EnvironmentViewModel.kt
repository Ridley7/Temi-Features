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
    private val speechManager: SpeechManager
) : ViewModel() {

    val environment = observeEnvironmentUseCase()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            null
        )

    private val _avatarState = MutableStateFlow(AvatarState.IDLE)
    val avatarState: StateFlow<AvatarState> = _avatarState

    fun getRecommendation(data: EnvironmentData): String {
        _avatarState.value = AvatarState.THINKING

        val recommendation = recommendationUseCase.execute(data)

        _avatarState.value = AvatarState.TALKING

        return recommendation
    }

    fun checkEnvironment(data: EnvironmentData){

        if(data.methane > 2000){
            _avatarState.value = AvatarState.ALERT
        }else{
            _avatarState.value = AvatarState.IDLE
        }

    }

    fun speakEnviroment(data: EnvironmentData){
        val message = """
        La temperatura actual es ${data.temperature} grados.
        El nivel de metano es ${data.methane}.
    """.trimIndent()

        _avatarState.value = AvatarState.TALKING

        //voiceManager.speak(message)
        speechManager.speak(
            SpeechMessage(
                text = message,
                priority = SpeechPriority.INFO
            )
        )
    }

    fun testVoice(){
        //voiceManager.speak("Hola, soy tu agente ambiental")
        speechManager.speak(
            SpeechMessage(
                text = "Hola, soy tu agente ambiental",
                priority = SpeechPriority.INFO
            )
        )
    }


}

