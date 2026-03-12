import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.franks.agenttemi.domain.model.AvatarState
import com.franks.agenttemi.domain.model.EnvironmentData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class EnvironmentViewModel(
    private val observeEnvironmentUseCase: ObserveEnvironmentUseCase,
    private val recommendationUseCase: GetEnvironmentRecommendationUseCase,
    private val voiceManager: VoiceManager
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

        voiceManager.speak(message)
    }

}

